package com.example.roomtrials;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserAndLibrary {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "userOwnerId"
    )
    public Library library;

    @Override
    public String toString() {
        return "UserAndLibrary{" +
                "user=" + user +
                ", library=" + library +
                '}';
    }
}