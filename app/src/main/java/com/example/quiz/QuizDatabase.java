package com.example.quiz;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.quiz.model.LoginDao;
import com.example.quiz.model.LoginUser;
import com.example.quiz.model.Question;
import com.example.quiz.model.QuestionDao;
import com.example.quiz.model.User;
import com.example.quiz.model.UserDao;

import java.io.BufferedReader;
import java.io.FileReader;

@Database(entities = {User.class, Question.class, LoginUser.class}, version = 3, exportSchema = false)
public abstract class QuizDatabase extends RoomDatabase {

    private static QuizDatabase instance;

    public abstract UserDao userDao();

    public abstract QuestionDao questionDao();

    public abstract LoginDao loginDao();

    public static synchronized QuizDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    QuizDatabase.class, "quiz_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
