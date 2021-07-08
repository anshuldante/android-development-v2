package com.example.roomtrials;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

public interface BaseDao<T> {

    @Insert
    void insert(T t);

    @Update
    int update(T t);

    @Delete
    void delete(T t);

    @Insert
    void insertList(List<T> t);

    @Update
    int updateList(List<T> t);

    @Delete
    int deleteList(List<T> t);
}
