package com.example.quiz;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class QuizRepository {
    private QuestionNumberDao questionNumberDao;
    private List<QuestionNumber> allQuestionNumbers;

    public QuizRepository(Application application) {
        QuizDatabase database = QuizDatabase.getInstance(application);
        questionNumberDao = database.questionNumberDao();
        allQuestionNumbers = questionNumberDao.getAllQuestion();
    }

    public void Insert(QuestionNumber questionNumber) {
        new InsertQuestionNumberAsyncTask(questionNumberDao).execute(questionNumber);
    }

    public List<QuestionNumber> getAllQuestionNumbers() {
        return allQuestionNumbers;
    }

    private static class InsertQuestionNumberAsyncTask extends AsyncTask<QuestionNumber, Void, Void> {
        private QuestionNumberDao questionNumberDao;

        private InsertQuestionNumberAsyncTask(QuestionNumberDao questionNumberDao) {
            this.questionNumberDao = questionNumberDao;
        }

        @Override
        protected Void doInBackground(QuestionNumber... questionNumbers) {
            questionNumberDao.Insert(questionNumbers[0]);
            return null;
        }
    }
}
