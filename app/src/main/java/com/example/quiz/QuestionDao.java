package com.example.quiz;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface QuestionDao {

    @Insert
    void Insert(Question question);

    @Update
    void Update(Question question);

    @Delete
    void Delete(Question question);

}
