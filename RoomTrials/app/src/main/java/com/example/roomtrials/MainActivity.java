package com.example.roomtrials;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> firstNames = Arrays.asList("Anshul", "Atharv", "Vibhuti", "Madhav");
    List<String> lastNames = Arrays.asList("Agrawal", "Bansal");

    UserDao userDao;
    LibraryDao libraryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        userDao = db.userDao();

        libraryDao = db.libraryDao();

        deleteUsers();
    }

    private void addUsersAndLibraries() {
        new Thread(() -> {
            firstNames.forEach(fName -> lastNames.forEach(lName -> {
                User user = new User(fName, lName);
                userDao.insertUser(user);
                libraryDao.insert(new Library(user.id));
            }));
            getAllUsers();
            getAllLibraries();
            getUsersAndLibraries();
        }).start();
    }

    private void getAllUsers() {
        new Thread(() -> {
            List<User> users = userDao.getAll();
            Log.i("Users: ", users.toString());
        }).start();
    }

    private void getAllLibraries() {
        new Thread(() -> {
            Log.i("Libraries: ", libraryDao.getAll().toString());
        }).start();
    }

    private void getUsersAndLibraries() {
        new Thread(() -> {
            Log.i("Libraries and users: ", userDao.getUsersAndLibraries().toString());
        }).start();
    }

    private void deleteUsers() {
        new Thread(() -> {
            userDao.nukeTable();
            deleteLibraries();
            addUsersAndLibraries();
        }).start();
    }

    private void deleteLibraries() {
        new Thread(() -> {
            libraryDao.nukeTable();
        }).start();
    }
}