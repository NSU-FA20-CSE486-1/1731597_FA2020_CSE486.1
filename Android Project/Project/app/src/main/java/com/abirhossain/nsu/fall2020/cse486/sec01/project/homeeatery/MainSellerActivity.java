package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainSellerActivity extends AppCompatActivity {
    private TextView clientName;
    private ImageView logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);
        clientName = findViewById(R.id.SellerName);
        logOutBtn = findViewById(R.id.Seller_logout_btn);
    }
}