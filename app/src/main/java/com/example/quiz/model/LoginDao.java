package com.example.quiz.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LoginDao {
    @Insert
    void insertDetails(LoginUser data);

    @Query("select * from login_user")
    LiveData<List<LoginUser>> getDetails();

    @Query("delete from login_user")
    void deleteAllData();
}
