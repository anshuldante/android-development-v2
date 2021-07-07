package com.example.roomtrials;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Library {

    @PrimaryKey(autoGenerate = true)
    public long libraryId;
    public long userOwnerId;

    public Library(long userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryId=" + libraryId +
                ", userOwnerId=" + userOwnerId +
                '}';
    }
}