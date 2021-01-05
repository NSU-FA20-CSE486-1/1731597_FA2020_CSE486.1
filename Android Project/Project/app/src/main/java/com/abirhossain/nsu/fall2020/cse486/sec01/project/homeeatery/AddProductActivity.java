package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
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

    }
}