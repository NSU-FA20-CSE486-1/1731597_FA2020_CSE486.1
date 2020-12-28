package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText emailET,passET;
    private TextView forgotTV, noAccount;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailET = findViewById(R.id.EmailET);
        passET = findViewById(R.id.PassET);
        forgotTV = findViewById(R.id.ForgotTv);
        loginBtn = findViewById(R.id.LoginBtn);
        noAccount = findViewById(R.id.NoAccount);

    }
}