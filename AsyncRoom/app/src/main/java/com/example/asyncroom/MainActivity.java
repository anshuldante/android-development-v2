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
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String NUMBER_REGEX = "\\d+";

    RxTrialDb db;
    UserDao userDao;
    Scheduler scheduler;
    TextView userDetailsTextView;
    EditText firstNameEt;
    EditText lastNameEt;
    EditText idEt;
    Button addUserBt;
    Button deleteUserBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), RxTrialDb.class, "Rx-Java-DB").build();
        userDao = db.userDao();
        scheduler = Schedulers.from(Executors.newFixedThreadPool(5));

        initViews();

        getAllUsers();
    }

    private void initViews() {
        userDetailsTextView = findViewById(R.id.am_tv_user_details);

        firstNameEt = findViewById(R.id.am_et_first_name);
        lastNameEt = findViewById(R.id.am_et_last_name);
        idEt = findViewById(R.id.am_et_user_id);

        addUserBt = findViewById(R.id.am_bt_add_user);
        deleteUserBt = findViewById(R.id.am_bt_delete_user);

        addUserBt.setOnClickListener(view ->
                addUser(firstNameEt.getText().toString(), lastNameEt.getText().toString()));

        deleteUserBt.setOnClickListener(view -> deleteUser(idEt.getText().toString())
        );
    }

    private void addUser(String firstName, String lastName) {
        Single<Long> insertedSingle = userDao.insert(new User(firstName, lastName));
        insertedSingle.subscribeOn(scheduler)
                .subscribe((l) -> Log.i("Users: ", "Inserted User with id: " + l),
                        (e) -> Log.e("Users: ", "Exception while adding user", e));
    }

    private void deleteUser(String id) {

        if (id != null && id.trim().matches(NUMBER_REGEX)) {

            userDao.getUserById(Integer.parseInt(id)).singleElement().subscribeOn(scheduler).subscribe((user -> {
                Log.i("Users: ", "Found the user: " + user.toString());
                userDao.delete(user).subscribeOn(scheduler)
                        .subscribe(integer -> Log.i("Users: ", "Deleted user with id: " + id),
                                throwable -> Log.e("Users: ", "Exception while deleting user with id: " + id, throwable))
                        .dispose();
            }), throwable -> Log.e("Users: ", "User with ID: " + id + " not found", throwable));
        } else {
            Log.i("Users: ", "Invalid userId: " + id);
        }
    }

    private void getAllUsers() {
        userDao.getAllUsers().subscribeOn(scheduler).subscribe(users -> {
                    Log.i("Users: ", "All Users: " + users.toString());
                    userDetailsTextView.setText(users.stream().map(Objects::toString).collect(Collectors.joining("\n")));

                }, throwable -> Log.e("Users: ", "Exception while fetching all users", throwable),
                () -> Log.i("Users: ", "Finished finding users"));
    }
}