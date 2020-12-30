package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterSeller extends AppCompatActivity implements LocationListener {

    private ImageView backBtn,gpsBtn,profileImage;
    private EditText nameET,phnET,passET,emailET,countryET,stateET,cityET,cAddressET,feeET,shopNameET;
    private Button regBtn;
    private TextView sellerTv;

    //permission
    private static final int LOCATION_REQUEST_CODE = 100;
    //array permission
    private String[] locationPermission;

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

        //permission array initializing
        locationPermission = new String[] {Manifest.permission.ACCESS_FINE_LOCATION};



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
                if (checkLocationPermission()){
                    //allowed
                    detectLocation();
                }
                else {
                    //disallowed
                    requestLocationPermission();
                }

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

    private void detectLocation() {

    }

    private boolean checkLocationPermission(){

        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)== (PackageManager.PERMISSION_GRANTED);
        return result;

    }

    private void requestLocationPermission(){
        ActivityCompat.requestPermissions(this,locationPermission,LOCATION_REQUEST_CODE);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case LOCATION_REQUEST_CODE:{
                if (grantResults.length>0){
                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (locationAccepted){
                        //location permission granted

                        detectLocation();

                    }
                    else {
                        //location permission denied
                        Toast.makeText(RegisterSeller.this, "Permission not granted", Toast.LENGTH_SHORT).show();

                    }
                }
            }
            break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



}