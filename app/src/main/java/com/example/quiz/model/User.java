package com.example.quiz.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String complete_name;

    private String username;

    private String password;

    private int high_score;

    public User(String complete_name, String username, String password) {
        this.complete_name = complete_name;
        this.username = username;
        this.password = password;
        this.high_score = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getComplete_name() {
        return complete_name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getHigh_score() {
        return high_score;
    }

    public void setComplete_name(String complete_name) {
        this.complete_name = complete_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHigh_score(int high_score) {
        this.high_score = high_score;
    }
}
