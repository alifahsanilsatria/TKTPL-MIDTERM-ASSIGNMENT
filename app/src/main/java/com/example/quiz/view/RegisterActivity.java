package com.example.quiz.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.quiz.R;
import com.example.quiz.model.User;
import com.example.quiz.viewmodel.QuizViewModel;

public class RegisterActivity extends AppCompatActivity {

    private QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
    }

    public void onClickRegister(View view) {
        EditText complete_name_edit_text = findViewById(R.id.name_entry);
        String complete_name = complete_name_edit_text.getText().toString();

        EditText username_edit_text = findViewById(R.id.username_entry);
        String username = username_edit_text.getText().toString();

        EditText password_edit_text = findViewById(R.id.password_entry);
        String password = password_edit_text.getText().toString();

        User user = new User(complete_name,username,password);

        quizViewModel.checkIfUsernameExistedOnRegister(user, this);
    }
}
