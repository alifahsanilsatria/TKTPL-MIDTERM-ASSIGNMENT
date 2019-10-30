package com.example.quiz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "option_table")
public class Option {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String option;

    public Option(String option) {
        this.option = option;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getOption() {
        return option;
    }
}
