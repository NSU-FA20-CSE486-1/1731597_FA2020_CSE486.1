package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.security.Permission;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class RegisterSeller extends AppCompatActivity implements LocationListener {

    private ImageView backBtn,gpsBtn,profileImage;
    private EditText nameET,phnET,passET,emailET,countryET,stateET,cityET,cAddressET,feeET,shopNameET;
    private Button regBtn;
    private TextView sellerTv;
    private double latitude=0.0,longitude=0.0;

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

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


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
        cameraPermission = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};


        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait for a moment");
        progressDialog.setCanceledOnTouchOutside(false);






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
                inputData();
            }
        });


    }

    private  String fullName,shopName, phoneNumber,deliveryFee,country,state,city,address,email,password;

    private void inputData() {

        fullName= nameET.getText().toString();
        shopName = shopNameET.getText().toString();
        phoneNumber = phnET.getText().toString();
        deliveryFee = feeET.getText().toString();
        country = countryET.getText().toString();
        state = stateET.getText().toString();
        city = cityET.getText().toString();
        address = cAddressET.getText().toString();
        email = emailET.getText().toString();
        password = passET.getText().toString();

        if(TextUtils.isEmpty(fullName)){
            Toast.makeText(RegisterSeller.this, "Information required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(shopName)){
            Toast.makeText(RegisterSeller.this, "shopName required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(RegisterSeller.this, "phoneNumber required", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(deliveryFee)){
            Toast.makeText(RegisterSeller.this, "deliveryFee required", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(RegisterSeller.this, "Enter right email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(RegisterSeller.this, "password required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(latitude== 0.0 || longitude == 0.0){
            Toast.makeText(RegisterSeller.this, "click gps button", Toast.LENGTH_SHORT).show();
            return;
        }

        createAccount();


    }

    private void createAccount() {

        progressDialog.setMessage("Creating account,Please wait");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //account successfully created
                        saveDataOnFirebase();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //account creating failed
                        progressDialog.dismiss();
                        Toast.makeText(RegisterSeller.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });
    }

    private void saveDataOnFirebase() {

        progressDialog.setMessage("Saving Data");
        String timestamp= ""+System.currentTimeMillis();
        if (image_uri==null)
        {
            //saving data without image
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("uid",""+firebaseAuth.getUid());
            hashMap.put("email",""+email);
            hashMap.put("name",""+fullName);
            hashMap.put("shopName",""+shopName);
            hashMap.put("phone",""+phoneNumber);
            hashMap.put("deliveryFee",""+deliveryFee);
            hashMap.put("country",""+country);
            hashMap.put("state",""+state);
            hashMap.put("city",""+city);
            hashMap.put("address",""+address);
            hashMap.put("latitude",""+latitude);
            hashMap.put("longitude",""+longitude);
            hashMap.put("timestamp",""+timestamp);
            hashMap.put("accountType","Seller");
            hashMap.put("online","true");
            hashMap.put("shopOpen","true");
            hashMap.put("profileImage","");

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
            ref.child(firebaseAuth.getUid()).setValue(hashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //db update
                            progressDialog.dismiss();
                            startActivity(new Intent(RegisterSeller.this,MainSellerActivity.class));
                            finish();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    startActivity(new Intent(RegisterSeller.this,MainSellerActivity.class));
                    finish();

                }
            });
        }

        else{
            //name and path of image
            String filePathAndName= "profile_images/"+""+firebaseAuth.getUid();
            //Uploading image
            StorageReference storageReference = FirebaseStorage.getInstance().getReference(filePathAndName);
            storageReference.putFile(image_uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //getting url of uploaded image
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful());

                            Uri downloadImageUri = uriTask.getResult();
                            if(uriTask.isSuccessful()){
                                HashMap<String,Object> hashMap = new HashMap<>();
                                hashMap.put("uid",""+firebaseAuth.getUid());
                                hashMap.put("email",""+email);
                                hashMap.put("name",""+fullName);
                                hashMap.put("shopName",""+shopName);
                                hashMap.put("phone",""+phoneNumber);
                                hashMap.put("deliveryFee",""+deliveryFee);
                                hashMap.put("country",""+country);
                                hashMap.put("state",""+state);
                                hashMap.put("city",""+city);
                                hashMap.put("address",""+address);
                                hashMap.put("latitude",""+latitude);
                                hashMap.put("longitude",""+longitude);
                                hashMap.put("timestamp",""+timestamp);
                                hashMap.put("accountType","Seller");
                                hashMap.put("online","true");
                                hashMap.put("shopOpen","true");
                                hashMap.put("profileImage",""+downloadImageUri);

                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                                ref.child(firebaseAuth.getUid()).setValue(hashMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                //db update
                                                progressDialog.dismiss();
                                                startActivity(new Intent(RegisterSeller.this,MainSellerActivity.class));
                                                finish();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressDialog.dismiss();
                                                startActivity(new Intent(RegisterSeller.this,MainSellerActivity.class));
                                                finish();

                                            }
                                        });
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterSeller.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }
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
        Toast.makeText(RegisterSeller.this, "Checking location.. ", Toast.LENGTH_LONG).show();
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
            Toast.makeText(RegisterSeller.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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
        Toast.makeText(RegisterSeller.this, "Please turn on your gps.. ", Toast.LENGTH_LONG).show();

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
                        Toast.makeText(RegisterSeller.this, "Permission not granted", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(RegisterSeller.this, "Permission not granted", Toast.LENGTH_SHORT).show();

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