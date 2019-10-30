package com.example.quiz;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.BufferedReader;
import java.io.FileReader;

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

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private QuestionNumberDao questionNumberDao;
        private QuestionDao questionDao;
        private OptionDao optionDao;

        private PopulateDbAsyncTask(QuizDatabase db) {
            questionNumberDao = db.questionNumberDao();
            questionDao = db.questionDao();
            optionDao = db.optionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                FileReader questionFr = new FileReader("question.txt");
                BufferedReader questionBr = new BufferedReader(questionFr);

                FileReader optionFr = new FileReader("options.txt");
                BufferedReader optionBr = new BufferedReader(optionFr);

                String questionRead = questionBr.readLine();
                while (questionRead != null) {
                    Question question = new Question(questionRead);
                    questionDao.Insert(question);
                    String[] optionArr = optionBr.readLine().split(",");
                    for (String opt : optionArr) {
                        if (opt.contains("$")) {
                            Option option = new Option(opt.substring(0,opt.length()-1));
                            optionDao.Insert(option);
                            questionNumberDao.Insert(new QuestionNumber(question.getId(), option.getId(), true));
                        }
                        else{
                            Option option = new Option(opt);
                            optionDao.Insert(option);
                            questionNumberDao.Insert(new QuestionNumber(question.getId(), option.getId(), false));
                        }
                    }
                    questionRead = questionBr.readLine();
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
            return null;
        }
    }
}
