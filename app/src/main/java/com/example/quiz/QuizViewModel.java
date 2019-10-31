package com.example.quiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class QuizViewModel extends AndroidViewModel {
    private QuizRepository repository;
    private List<QuestionNumber> allQuestionNumbers;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        repository = new QuizRepository(application);
        allQuestionNumbers = repository.getAllQuestionNumbers();
    }

    public List<QuestionNumber> getAllQuestionNumbers() {
        return allQuestionNumbers;
    }
}
