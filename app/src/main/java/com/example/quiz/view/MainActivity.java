package com.example.quiz.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.quiz.R;
import com.example.quiz.model.Question;
import com.example.quiz.viewmodel.QuizViewModel;

public class MainActivity extends AppCompatActivity {

    private QuizViewModel quizViewModel;
    public boolean questionExisted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizViewModel = ViewModelProviders.of(MainActivity.this).get(QuizViewModel.class);
        quizViewModel.getAllQuestionsForMainActivity(this);
    }

    public void loginPage(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void registerPage(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void addQuiz(View view) {
        quizViewModel.questionInsertion(
                new Question("Kapan Perang Dunia I dimulai ?", "1923","1914","1917",2),
                new Question("Di mana tempat lahir Adolf Hitler ?", "France","Germany", "Austria", 3),
                new Question("Di mana tempat John F. Kennedy dibunuh ?", "New York","Austin","Dallas",3),
                new Question("Kapan dekade ketika Amerika terlibat di Perang Korea ?", "1970","1950","1920",2),
                new Question("Penyakit yang merusak dan membunuh sepertiga populasi Eropa pada abad ke-14 dikenal sebagai ?", "The White Death","The Black Plague","The Bubonic Plague",3),
                new Question("Siapakah pemimpin armada Belanda yang datang pertama kali ke Indonesia pada tahun 1596 ?", "Wybrand Van Wawyk", "Jan Pieterszoon Coen", "Cornellis de Houtman",3),
                new Question("Siapakah Gubernur Jenderal VOC (Vereenigde Oost Indische Compagnie) yang pertama ?", "Wybrand Van Wawyk", "Jan Pieterszoon Coen", "Cornellis de Houtman",2),
                new Question("Melalui perjanjian apakah Pemerintah Hindia Belanda menyerah tanpa syarat kepada Jepang pada tanggal 8 Maret 1942 dan selanjutnya Gubernur Hindia Belanda Garda van Starkenborgh Stachouwerm dibawa Jepang ke pulau Formosa (Taiwan) ?", "Perjanjian Kalijati", "Perjanjian Magelang", "Perjanjian Batavia", 1),
                new Question("Penggagas Serangan Umum ke kota Yogyakarta yang ketika itu tengah diduduki Belanda adalah ?", "Sri Sultan Hamengku Buwono IX","Letnan Kolonel Suharto","Panglima Besar Jenderal Sudirman",1),
                new Question("Pada tanggal 18 September 1948 di Madiun para tokoh Partai Komunis Indonesia (PKI) memproklamirkan berdirinya Republik Soviet Indonesia. Pemimpin pemberontakan tersebut yang akhirnya tertembak mati adalah ?", "Hendricus Sneevliet","Semaun","Muso",3)
        );
        findViewById(R.id.button2).setVisibility(View.GONE);
    }
}
