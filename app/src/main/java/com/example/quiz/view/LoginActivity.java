package com.example.quiz.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.quiz.DataBinding.Listener;
import com.example.quiz.R;
import com.example.quiz.databinding.ActivityLoginBinding;
import com.example.quiz.model.LoginUser;
import com.example.quiz.model.User;
import com.example.quiz.viewmodel.QuizViewModel;

import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements Listener {

    public QuizViewModel quizViewModel;

    public boolean authorized;
    public boolean logged_in = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        quizViewModel = ViewModelProviders.of(LoginActivity.this).get(QuizViewModel.class);
        quizViewModel.getLoginUserData().observe(this, this::onLoginUser);
    }

    public void onClickLogin(View view) {

        String username = ((TextView)findViewById(R.id.username_entry)).getText().toString();
        String password = ((TextView)findViewById(R.id.password_entry)).getText().toString();

        String[] userCredentials = {username, password};
        quizViewModel.checkCredentialsTruth(userCredentials, this);
    }

    public void onLoginUser(List<LoginUser> loginUsers) {
        if(!logged_in || loginUsers.size() == 0) {
            return;
        }

        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("username", loginUsers.get(0).getUsername());
        startActivity(intent);
    }
}
