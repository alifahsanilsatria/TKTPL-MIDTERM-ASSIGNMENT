package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        List<QuestionNumber> allQuestionNumbers = quizViewModel.getAllQuestionNumbers();

    }
}
