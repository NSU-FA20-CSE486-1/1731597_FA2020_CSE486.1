package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainUserActivity extends AppCompatActivity {

    private TextView userName;
    private ImageView logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        userName = findViewById(R.id.ClientName);
        logOutBtn = findViewById(R.id.Client_logout_btn);
        

    }
}