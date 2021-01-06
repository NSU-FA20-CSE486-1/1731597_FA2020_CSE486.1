package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddProductActivity extends AppCompatActivity {
    private ImageView backBtn,food_image;
    private EditText food_name,food_desc,food_quantity,food_price, food_discount_price,food_discount_text;
    private TextView food_cat;
    private SwitchCompat discountSwitch;
    private Button food_add;


    //permission constants
    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 300;
    //image pick constants
    private static final int IMAGE_PICK_GALLERY_CODE = 400;
    private static final int IMAGE_PICK_CAMERA_CODE = 500;
    //permissions array
    private  String[] cameraPermissions;
    private String[] storagePermission;
    // picked image uri
    private Uri image_uri;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        backBtn = findViewById(R.id.backBtnAddProduct);
        food_image = findViewById(R.id.food_image);
        food_name = findViewById(R.id.food_nameET);
        food_desc = findViewById(R.id.food_descET);
        food_cat = findViewById(R.id.food_catET);
        food_quantity = findViewById(R.id.food_quantityET);
        discountSwitch = findViewById(R.id.discountET);
        food_price = findViewById(R.id.food_priceET);
        food_discount_price = findViewById(R.id.food_discount_priceET);
        food_discount_text = findViewById(R.id.food_discount_Text);
        food_add = findViewById(R.id.food_addBtn);

        //initializing permission array
        cameraPermissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        food_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //show dialog option to pick image
                showImagePickDialog();
            }
        });


    }

    private void showImagePickDialog() {
        //options in the dialog
        String[] options = {"Camera","Gallery"};
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setTitle("Select Image")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            //selected camera
                            if(checkCameraPermission()){
                                //have camera permission
                                pickFromCamera();
                            }
                            else {
                                //no camera permission
                                requestCameraPermission();
                            }
                        }
                        else {
                            if(checkCameraPermission()){
                                //storage permission given
                                pickFromGallery();
                            }

                            else {
                                // no storage permission
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
        contentValues.put(MediaStore.Images.Media.TITLE,"Temp_Image_Title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"Temp_Image_Description");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(intent,IMAGE_PICK_CAMERA_CODE);
    }

    private boolean checkStoragePermission(){

        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                (PackageManager.PERMISSION_GRANTED);
        return result;
    }
    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this,storagePermission,STORAGE_REQUEST_CODE);

    }
    private boolean checkCameraPermission(){
        boolean result =  ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==
                (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }
    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);

    }
    // 

}