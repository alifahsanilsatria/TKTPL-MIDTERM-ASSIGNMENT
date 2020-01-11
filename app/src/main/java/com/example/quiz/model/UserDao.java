package com.example.quiz.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("select * from user_table where username=:uname")
    User[] loadAllUsers(String uname);

    @Query("select * from user_table where username=:uname and password=:pw")
    User[] loadUserCredentials(String uname, String pw);

    @Query("update user_table set high_score = :high_score_new where username=:username")
    void updateUserHighScore(int high_score_new, String username);

}
