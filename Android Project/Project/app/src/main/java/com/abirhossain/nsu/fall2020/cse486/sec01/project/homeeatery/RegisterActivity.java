package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private ImageView backBtn,gpsBtn,profileImage;
    private EditText nameET,phnET,passET,emailET,countryET,stateET,cityET,cAddressET;
    private Button regBtn;
    private TextView sellerTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backBtn= findViewById(R.id.ReturnReg);
        gpsBtn = findViewById(R.id.LocationDetect);
        profileImage = findViewById(R.id.userImage);
        nameET = findViewById(R.id.SignUp_UserName_input);
        phnET = findViewById(R.id.SignUp_phone_number_input);
        passET = findViewById(R.id.signUp_password_input);
        emailET = findViewById(R.id.SignUp_email_input);
        countryET = findViewById(R.id.countryET);
        stateET = findViewById(R.id.stateET);
        cityET = findViewById(R.id.cityET);
        cAddressET = findViewById(R.id.CompleteAddress);
        regBtn= findViewById(R.id.RegisterBtn);
        sellerTv = findViewById(R.id.SellerTv);





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
        sellerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Seller Registration activity open
                
            }
        });





    }
}