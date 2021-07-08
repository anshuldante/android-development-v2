package com.example.asyncroom.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.asyncroom.entity.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface UserDao {

    @Insert
    Single<Long> insert(User user);

    @Delete
    Single<Integer> delete(User user);

    @Query("SELECT * FROM user WHERE id = :userId")
    Observable<User> getUserById(int userId);

    @Query("SELECT * FROM user")
    Observable<List<User>> getAllUsers();
}
