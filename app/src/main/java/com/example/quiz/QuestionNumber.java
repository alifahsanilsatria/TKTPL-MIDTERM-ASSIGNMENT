package com.example.quiz;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "question_number_table", primaryKeys = { "question_id", "option_id" },
        foreignKeys = { @ForeignKey(entity = Question.class, parentColumns = "id", childColumns = "question_id"),
                        @ForeignKey(entity = Option.class, parentColumns = "id", childColumns = "option_id")
                      })
public class QuestionNumber {

    private int question_id;
    private int option_id;
    private boolean isRight;

    public QuestionNumber(int question_id, int option_id, boolean isRight) {
        this.question_id = question_id;
        this.option_id = option_id;
        this.isRight = isRight;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public int getOption_id() {
        return option_id;
    }

    public boolean isRight() {
        return isRight;
    }
}
