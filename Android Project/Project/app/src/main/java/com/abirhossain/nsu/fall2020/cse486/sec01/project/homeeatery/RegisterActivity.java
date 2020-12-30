package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity implements LocationListener {

    private ImageView backBtn,gpsBtn,profileImage;
    private EditText nameET,phnET,passET,emailET,countryET,stateET,cityET,cAddressET;
    private Button regBtn;
    private TextView sellerTv;

    private double latitude,longitude;

    //permission
    private static final int LOCATION_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 300;
    //Constant for picking image
    private static final int IMAGE_PICK_GALLERY_CODE = 400;
    private static final int IMAGE_PICK_CAMERA_CODE = 500;

    //array permission
    private String[] locationPermission;
    private String[] cameraPermission;
    private String[] storagePermission;
    //uri to pick image
    private Uri image_uri;

    private LocationManager locationManager;



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
        //permission array initializing
        locationPermission = new String[] {Manifest.permission.ACCESS_FINE_LOCATION};
        cameraPermission = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};





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

                showImagePickDialog();
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
                startActivity(new Intent(RegisterActivity.this,RegisterSeller.class));

            }
        });

    }

    private void showImagePickDialog() {

        String[] options =  {"Camera","Gallery"};
        //building a dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chose Image")
                .setItems(options, new DialogInterface.OnClickListener() {
                    //handling user choices
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which==0){
                            //user chose camera
                            if (checkCameraPermission()){
                                //camera permission was allowed
                                pickFromCamera();

                            }
                            else {
                                //camera permission denied
                                requestCameraPermission();

                            }
                        }
                        else{
                            //user chose gallery
                            if (checkStoragePermission()){
                                //Storage permission was allowed
                                pickFromGallery();


                            }
                            else {
                                //Storage permission was denied
                                requestStoragePermission();

                            }
                        }

                    }
                })
                .show();
    }

    private void pickFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_GALLERY_CODE);

    }
    private void pickFromCamera(){

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE,"Temp_image Title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"Temp_image Description");

        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(intent,IMAGE_PICK_CAMERA_CODE);



    }



    private void detectLocation() {
        Toast.makeText(RegisterActivity.this, "Checking location.. ", Toast.LENGTH_LONG).show();
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

    }

    private boolean checkLocationPermission(){

        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)== (PackageManager.PERMISSION_GRANTED);
        return result;

    }

    private void requestLocationPermission(){
        ActivityCompat.requestPermissions(this,locationPermission,LOCATION_REQUEST_CODE);
    }

    private boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this,storagePermission,STORAGE_REQUEST_CODE);

    }
    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)==
                (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }
    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermission,CAMERA_REQUEST_CODE);

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        // detected location
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        findAddress();
    }

    private void findAddress() {

        Geocoder geocoder;
        List<Address> addresses;

        geocoder = new Geocoder(this, Locale.getDefault());

        try {

            addresses = geocoder.getFromLocation(latitude,longitude,1);
            // full address in address string
            String address = addresses.get(0).getAddressLine(0);
            String city= addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            // setting the address in the signup activity
            countryET.setText(country);
            stateET.setText(state);
            cityET.setText(city);
            cAddressET.setText(address);





        }
        catch (Exception e){
            Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Toast.makeText(RegisterActivity.this, "Please turn on your gps.. ", Toast.LENGTH_LONG).show();

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
                        Toast.makeText(RegisterActivity.this, "Permission not granted", Toast.LENGTH_SHORT).show();

                    }
                }
            }
            break;
            case CAMERA_REQUEST_CODE:{
                if (grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && storageAccepted){
                        //location permission granted
                        pickFromCamera();


                    }
                    else {
                        //location permission denied
                        Toast.makeText(RegisterActivity.this, "Permission not granted", Toast.LENGTH_SHORT).show();

                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if (grantResults.length>0){

                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted){
                        //location permission granted
                        pickFromGallery();


                    }
                    else {
                        //location permission denied
                        Toast.makeText(RegisterActivity.this, "Permission not granted", Toast.LENGTH_SHORT).show();

                    }
                }
            }
            break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode== RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                image_uri = data.getData();
                profileImage.setImageURI(image_uri);

            }
            else if (requestCode == IMAGE_PICK_GALLERY_CODE){
                profileImage.setImageURI(image_uri);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}