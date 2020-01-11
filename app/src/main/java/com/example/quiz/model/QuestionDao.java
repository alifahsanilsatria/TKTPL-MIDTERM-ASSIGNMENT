package com.example.quiz.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quiz.model.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void Insert(Question... question);

    @Update
    void Update(Question question);

    @Delete
    void Delete(Question question);

    @Query("select * from question_table")
    Question[] getAllQuestions();

}
