package com.example.asyncroom.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.asyncroom.entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class RxTrialDb extends RoomDatabase {
    public abstract UserDao userDao();
}
