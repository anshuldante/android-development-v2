package com.example.roomtrials;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class PrePopulateCallback extends RoomDatabase.Callback {
    private AppDatabase appDd;

    public PrePopulateCallback(AppDatabase appDd) {
        this.appDd = appDd;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        appDd.userDao().insert(new User("Neeta", "Agrawal"));
    }
}
