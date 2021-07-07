package com.example.roomtrials;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User users);

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("DELETE FROM user")
    void nukeTable();

    @Query("SELECT * FROM user WHERE rowid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Delete
    void delete(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User... users);

    @Update
    int updateUsers(User... users);

    @Delete
    int deleteUsers(User... users);

    @Query("SELECT first_name, last_name FROM user")
    List<User.NameTuple> loadFullName();

    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
    User[] loadAllUsersBetweenAges(int minAge, int maxAge);

    @Transaction
    @Query("SELECT * FROM User")
    List<UserAndLibrary> getUsersAndLibraries();

    @Transaction
    @Query("SELECT * FROM User")
    public List<UserWithPlaylists> getUsersWithPlaylists();

    @Transaction
    @Query("SELECT * FROM User")
    public List<UserWithPlaylistsAndSongs> getUsersWithPlaylistsAndSongs();
}