package com.example.quiz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionNumberDao {

    @Insert
    void Insert(QuestionNumber questionNumber);

    @Query("select question,option,isRight from  question_table, option_table, question_number_table where question_table.id = question_id and option_table.id = option_id")
    List<List<String>> getAllQuestion();

}
