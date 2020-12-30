package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterSeller extends AppCompatActivity {

    private ImageView backBtn,gpsBtn,profileImage;
    private EditText nameET,phnET,passET,emailET,countryET,stateET,cityET,cAddressET,feeET,shopNameET;
    private Button regBtn;
    private TextView sellerTv;

    //permission

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_seller);

        backBtn= findViewById(R.id.ReturnSellerReg);
        gpsBtn = findViewById(R.id.SellerLocationDetect);
        profileImage = findViewById(R.id.SelleruserImage);
        nameET = findViewById(R.id.SellerSignUp_UserName_input);
        phnET = findViewById(R.id.SellerSignUp_phone_number_input);
        passET = findViewById(R.id.SellersignUp_password_input);
        emailET = findViewById(R.id.SellerSignUp_email_input);
        countryET = findViewById(R.id.SellercountryET);
        stateET = findViewById(R.id.SellerstateET);
        cityET = findViewById(R.id.SellercityET);
        cAddressET = findViewById(R.id.SellerCompleteAddress);
        regBtn= findViewById(R.id.SellerRegisterBtn);
        sellerTv = findViewById(R.id.SellerSellerTv);
        feeET = findViewById(R.id.SellerSignUp_fee_input);
        shopNameET = findViewById(R.id.SellerSignUp_Shop_Name_input);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        gpsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // current location detection
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selecting image
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //registration process
            }
        });


    }
}