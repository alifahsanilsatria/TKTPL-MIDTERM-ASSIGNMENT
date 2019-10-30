package com.example.quiz;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Insert
    void Insert(User user);

    @Update
    void Update(User user);

    @Delete
    void Delete(User user);

}
