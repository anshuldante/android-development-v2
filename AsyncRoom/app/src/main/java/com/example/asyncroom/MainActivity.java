package com.example.asyncroom;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.asyncroom.data.RxTrialDb;
import com.example.asyncroom.data.UserDao;
import com.example.asyncroom.entity.User;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String NUMBER_REGEX = "\\d+";

    private UserDao userDao;
    private Scheduler scheduler;
    private TextView userDetailsTextView;
    private EditText firstNameEt;
    private EditText lastNameEt;
    private EditText idEt;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxTrialDb db = Room.databaseBuilder(getApplicationContext(), RxTrialDb.class, "Rx-Java-DB").build();
        userDao = db.userDao();
        executorService = Executors.newFixedThreadPool(5);
        scheduler = Schedulers.from(executorService);
        initViews();
        getAllUsers();
    }

    private void initViews() {
        userDetailsTextView = findViewById(R.id.am_tv_user_details);

        firstNameEt = findViewById(R.id.am_et_first_name);
        lastNameEt = findViewById(R.id.am_et_last_name);
        idEt = findViewById(R.id.am_et_user_id);

        Button addUserBt = findViewById(R.id.am_bt_add_user);
        Button deleteUserBt = findViewById(R.id.am_bt_delete_user);

        addUserBt.setOnClickListener(view ->
                addUser(firstNameEt.getText().toString(), lastNameEt.getText().toString()));
        deleteUserBt.setOnClickListener(view -> deleteUser(idEt.getText().toString())
        );
    }

    private void addUser(String firstName, String lastName) {
        executorService.submit(() -> {
            try {
                userDao.insert(new User(firstName, lastName));
                Log.i("Users: ", "Inserted User: " + firstName + " " + lastName);
            } catch (Exception e) {
                Log.e("Users: ", "Exception while adding user", e);
            }
        });
    }

    private void deleteUser(String id) {
        if (id != null && id.trim().matches(NUMBER_REGEX)) {
            executorService.submit(() -> {
                try {
                    User user = userDao.getUserById(Integer.parseInt(id));
                    Log.i("Users: ", "Found the user: " + user.toString());
                    try {
                        userDao.delete(user);
                        Log.i("Users: ", "Deleted user with id: " + id);
                    } catch (Exception e) {
                        Log.e("Users: ", "Exception while deleting user with id: " + id, e);
                    }
                } catch (Exception e) {
                    Log.e("Users: ", "User with ID: " + id + " not found", e);
                }
            });
        } else {
            Log.i("Users: ", "Invalid userId: " + id);
        }
    }

    private void getAllUsers() {
        Disposable disposable = userDao.getAllUsers().subscribeOn(scheduler).subscribe(users -> {
                    Log.i("Users: ", "All Users: " + users.toString());
                    runOnUiThread(() -> {
                        if (users.size() == 0) {
                            userDetailsTextView.setText("No users!");
                        } else {
                            userDetailsTextView.setText(users.stream().map(Objects::toString).collect(Collectors.joining("\n")));
                        }
                    });
                }, throwable -> Log.e("Users: ", "Exception while fetching all users", throwable),
                () -> Log.i("Users: ", "Finished finding users"));
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}