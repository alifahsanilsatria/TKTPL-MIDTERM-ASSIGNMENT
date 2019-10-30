package com.example.quiz;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Question.class, Option.class, QuestionNumber.class}, version = 1)
public abstract class QuizDatabase extends RoomDatabase {

    private static QuizDatabase instance;

    public abstract UserDao userDao();

    public abstract QuestionDao questionDao();

    public abstract OptionDao optionDao();

    public abstract QuestionNumberDao questionNumberDao();

    public static synchronized QuizDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    QuizDatabase.class, "quiz_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
