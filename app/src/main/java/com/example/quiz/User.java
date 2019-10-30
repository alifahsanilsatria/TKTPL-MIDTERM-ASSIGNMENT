package com.example.quiz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String complete_name;

    private String username;

    private String password;

    public User(String complete_name, String username, String password) {
        this.complete_name = complete_name;
        this.username = username;
        this.password = password;
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
}
