package com.example.quiz;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.LiveData;

import com.example.quiz.model.LoginDao;
import com.example.quiz.model.LoginUser;
import com.example.quiz.model.Question;
import com.example.quiz.model.QuestionDao;
import com.example.quiz.model.User;
import com.example.quiz.model.UserDao;
import com.example.quiz.view.LoginActivity;
import com.example.quiz.view.MainActivity;
import com.example.quiz.view.ProfileActivity;
import com.example.quiz.view.QuizActivity;
import com.example.quiz.view.RegisterActivity;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class QuizRepository {
    private QuestionDao questionDao;
    private List<Question> allQuestions;

    private LoginDao loginDao;
    private LiveData<List<LoginUser>> loginUser;

    private UserDao userDao;


    public QuizRepository(Application application) {
        QuizDatabase database = QuizDatabase.getInstance(application);
        loginDao = database.loginDao();
        userDao = database.userDao();
        questionDao = database.questionDao();
        loginUser = loginDao.getDetails();
    }

    public void checkCredentialsTruth(String[] userCredentials, LoginActivity activity) {
        new credentialsTruthChecking(userDao, activity).execute(userCredentials);
    }

    private static class credentialsTruthChecking extends AsyncTask<String,Void,User[]> {
        private UserDao userDao;
        private WeakReference<LoginActivity> activityReference;

        private credentialsTruthChecking(UserDao userDao, LoginActivity context) {
            this.userDao = userDao;
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected User[] doInBackground(String... userCredentials) {
            return userDao.loadUserCredentials(userCredentials[0], userCredentials[1]);
        }

        @Override
        protected void onPostExecute(User[] users) {
            LoginActivity loginActivity = activityReference.get();
            if (users.length == 0) {
                ((TextView)loginActivity.findViewById(R.id.textView5)).setText("USERNAME OR PASSWORD IS WRONG");
                loginActivity.authorized = false;
            }
            else {
                loginActivity.authorized = true;
            }

            if (loginActivity.authorized) {
                loginActivity.quizViewModel.deleteLoginUser();
                loginActivity.logged_in = true;
                loginActivity.quizViewModel.insertLoginUser(new LoginUser(users[0].getUsername(), users[0].getPassword()), loginActivity);
            }
        }
    }

    public void checkIfUsernameExistedOnRegister(User user, RegisterActivity activity) {
        new checkUsernameOnRegister(userDao, activity).execute(user);
    }

    private static class checkUsernameOnRegister extends AsyncTask<User,Void,Boolean> {
        private UserDao userDao;
        private WeakReference<RegisterActivity> activityReference;
        private boolean usernameExist;

        private checkUsernameOnRegister(UserDao userDao, RegisterActivity context) {
            this.userDao = userDao;
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Boolean doInBackground(User... user) {
            User[] allUser = userDao.loadAllUsers(user[0].getUsername());
            if (allUser.length == 0) {
                userDao.insert(user[0]);
                usernameExist = false;
                allUser = userDao.loadAllUsers(user[0].getUsername());
            }
            else {
                usernameExist = true;
            }
            return usernameExist;
        }

        @Override
        protected void onPostExecute(Boolean usernameExist) {
            RegisterActivity activity = activityReference.get();
            if(!usernameExist) {
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
            }
            else {
                TextView user_exist_notification = activity.findViewById(R.id.user_exist);
                user_exist_notification.setText("This username already exist");
            }
        }
    }

    public LiveData<List<LoginUser>> getLoginUserData() {
        return loginUser;
    }

    public void insertRegisterUserData(User data) {
        new RegisterInsertion(userDao).execute(data);
    }

    private static class RegisterInsertion extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private RegisterInsertion(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... data) {

            userDao.insert(data[0]);
            return null;
        }
    }

    public void getAllQuestions(QuizActivity activity) {
        new AllQuestions(questionDao, activity).execute();
    }

    private static class AllQuestions extends AsyncTask<Void, Void, Question[]> {
        private QuestionDao questionDao;
        private WeakReference<QuizActivity> activityReference;

        private AllQuestions(QuestionDao questionDao, QuizActivity context) {
            this.questionDao = questionDao;
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Question[] doInBackground(Void... voids) {
            return questionDao.getAllQuestions();
        }

        @Override
        protected void onPostExecute(Question[] question) {
            int jumlah_pertanyaan = question.length;
            QuizActivity quizActivity = activityReference.get();
            quizActivity.questionList = Arrays.asList(question);
            quizActivity.questionCountTotal = jumlah_pertanyaan;
            quizActivity.showNextQuestion();
        }
    }

    public void getAllQuestionsForMainActivity(MainActivity activity) {
        new AllQuestionsForMainActivity(questionDao, activity).execute();
    }

    private static class AllQuestionsForMainActivity extends AsyncTask<Void, Void, Question[]> {
        private QuestionDao questionDao;
        private WeakReference<MainActivity> activityReference;

        private AllQuestionsForMainActivity(QuestionDao questionDao, MainActivity context) {
            this.questionDao = questionDao;
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Question[] doInBackground(Void... voids) {
            return questionDao.getAllQuestions();
        }

        @Override
        protected void onPostExecute(Question[] question) {
            MainActivity mainActivity = activityReference.get();
            if (question.length > 0) {
                mainActivity.findViewById(R.id.button2).setVisibility(View.GONE);
            }
        }
    }


    public void insertQuestion(Question... questions) {
        new QuestionInsertion(questionDao).execute(questions);
    }

    private static class QuestionInsertion extends AsyncTask<Question, Void, Void> {
        private QuestionDao questionDao;

        private QuestionInsertion(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Question... question) {
            questionDao.Insert(question);
            return null;
        }
    }

    public void insertLoginUserData(LoginUser loginUser, LoginActivity activity) {
        new LoginUserInsertion(loginDao, activity).execute(loginUser);
    }

    private static class LoginUserInsertion extends AsyncTask<LoginUser, Void, Void> {
        private LoginDao loginDao;
        private WeakReference<LoginActivity> activityReference;

        private LoginUserInsertion(LoginDao loginDao, LoginActivity context) {
            this.loginDao = loginDao;
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            LoginActivity loginActivity = activityReference.get();
            loginActivity.logged_in = true;
        }

        @Override
        protected Void doInBackground(LoginUser... loginUsers) {
            loginDao.insertDetails(loginUsers[0]);
            return null;
        }
    }

    public void deleteLoginUserData() {
        new LoginUserDeletion(loginDao).execute();
    }

    private static class LoginUserDeletion extends AsyncTask<Void, Void, Void> {
        private LoginDao loginDao;

        private LoginUserDeletion(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            loginDao.deleteAllData();
            return null;
        }
    }

    public void getUserHighScore(String username, ProfileActivity activity) {
        new UserHighScoreRetrieval(userDao, activity).execute(username);
    }

    private static class UserHighScoreRetrieval extends AsyncTask<String, Void, Integer> {
        private UserDao userDao;
        private WeakReference<ProfileActivity> activityReference;

        private UserHighScoreRetrieval(UserDao userDao, ProfileActivity context) {
            this.userDao = userDao;
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Integer doInBackground(String... username) {
            User[] users = userDao.loadAllUsers(username[0]);
            int jumlah_user = users.length;
            return users[0].getHigh_score();
        }

        @Override
        protected void onPostExecute(Integer highScore) {
            ProfileActivity activity = activityReference.get();
            ((TextView)activity.findViewById(R.id.text_view_highscore)).setText("Highscore: " + highScore);
        }
    }

    public void setUserHighScore(String[] userData, ProfileActivity activity) {
        new UserHighScoreModification(userDao, activity).execute(userData);
    }

    private static class UserHighScoreModification extends AsyncTask<String, Void, Integer> {
        private UserDao userDao;
        private WeakReference<ProfileActivity> activityReference;

        private UserHighScoreModification(UserDao userDao, ProfileActivity context) {
            this.userDao = userDao;
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Integer doInBackground(String... userData) {
            int highScoreNew = Integer.parseInt(userData[0]);
            userDao.updateUserHighScore(highScoreNew,userData[1]);
            return highScoreNew;

    }
        @Override
        protected void onPostExecute(Integer highScoreNew) {
            ProfileActivity activity = activityReference.get();
            ((TextView)activity.findViewById(R.id.text_view_highscore)).setText("Highscore: " + highScoreNew);
        }
    }


}
