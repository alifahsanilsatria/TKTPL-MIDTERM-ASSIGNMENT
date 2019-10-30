package com.example.quiz;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface OptionDao {

    @Insert
    void Insert(Option option);

    @Update
    void Update(Option option);

    @Delete
    void Delete(Option option);

}
