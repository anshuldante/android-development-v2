package com.example.asyncroom.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.asyncroom.entity.User;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user WHERE id = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM user")
    Observable<List<User>> getAllUsers();
}
