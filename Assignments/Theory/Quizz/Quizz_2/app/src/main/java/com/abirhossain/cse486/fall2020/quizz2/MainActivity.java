package com.abirhossain.cse486.fall2020.quizz2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText eng_word,ben_word;
    private Button saveBtn,dictionaryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eng_word = findViewById(R.id.eng_word);
        ben_word = findViewById(R.id.ben_word);
        saveBtn = findViewById(R.id.saveBtn);
        dictionaryBtn = findViewById(R.id.dictionaryBtn);
        
    }
}