package com.example.quiz.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quiz.model.LoginUser;
import com.example.quiz.QuizRepository;
import com.example.quiz.model.Question;
import com.example.quiz.model.QuestionDao;
import com.example.quiz.model.User;
import com.example.quiz.view.LoginActivity;
import com.example.quiz.view.MainActivity;
import com.example.quiz.view.ProfileActivity;
import com.example.quiz.view.QuizActivity;
import com.example.quiz.view.RegisterActivity;

import java.util.List;

public class QuizViewModel extends AndroidViewModel {
    private QuizRepository repository;

    private LiveData<List<LoginUser>> loginUser;


    public QuizViewModel(@NonNull Application application) {
        super(application);
        repository = new QuizRepository(application);
//        allQuestionNumbers = repository.getAllQuestionNumbers();
        loginUser = repository.getLoginUserData();
    }

    public void insertLoginUser(LoginUser data, LoginActivity activity) {
        repository.insertLoginUserData(data, activity);
    }

    public void deleteLoginUser() {
        repository.deleteLoginUserData();
    }

    public void insertUser(User data) {
        repository.insertRegisterUserData(data);
    }

    public void checkIfUsernameExistedOnRegister(User user, RegisterActivity activity) {
        repository.checkIfUsernameExistedOnRegister(user, activity);
    }

    public void checkCredentialsTruth(String[] userCredentials, LoginActivity activity) {
        repository.checkCredentialsTruth(userCredentials, activity);
    }

    public LiveData<List<LoginUser>> getLoginUserData() {
        return loginUser;
    }

    public void getAllQuestions(QuizActivity activity) {
        repository.getAllQuestions(activity);
    }

    public void getUserHighScore(String username, ProfileActivity activity) {
        repository.getUserHighScore(username, activity);
    }

    public void setUserHighScore(String[] userData, ProfileActivity activity) {
        repository.setUserHighScore(userData, activity);
    }

    public void questionInsertion(Question... questions) {
        repository.insertQuestion(questions);
    }

    public void getAllQuestionsForMainActivity(MainActivity activity) {
        repository.getAllQuestionsForMainActivity(activity);
    }

}
