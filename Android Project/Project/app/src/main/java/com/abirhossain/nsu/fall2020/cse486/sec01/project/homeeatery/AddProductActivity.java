package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
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
    private static final int IMAGE_PICK_CAMERA_CODE = 400;
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


            }
        });


    }
}