package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainSellerActivity extends AppCompatActivity {
    private TextView clientName;
    private ImageView logOutBtn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);
        clientName = findViewById(R.id.SellerName);
        logOutBtn = findViewById(R.id.Seller_logout_btn);
        firebaseAuth = FirebaseAuth.getInstance();
        checkVendor();

    }

    private void checkVendor() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user==null){
            startActivity(new Intent(MainSellerActivity.this,LoginActivity.class));
            finish();
        }
        else
        {
           
        }
    }
}