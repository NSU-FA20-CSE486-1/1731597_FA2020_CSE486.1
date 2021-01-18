package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private ImageView shopImage,backBtnShopDetails,callRestaurant,filterFoodBtn,cartIV;
    private TextView  ShopNameTV,shopStatusTV,feeTV,ShopEmailTV,ShopPhoneTV,ShopAddressTV,
            filteredFoodTV;
    private EditText searchFoodsET;
    private RecyclerView foodsShowToClientRV;
    private String shopUid;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        shopImage = findViewById(R.id.shopImage);
        backBtnShopDetails = findViewById(R.id.backBtnShopDetails);
        callRestaurant = findViewById(R.id.callRestaurant);
        ShopNameTV = findViewById(R.id.ShopNameTV);
        shopStatusTV = findViewById(R.id.shopStatusTV);
        feeTV = findViewById(R.id.feeTV);
        ShopPhoneTV = findViewById(R.id.ShopPhoneTV);
        ShopEmailTV = findViewById(R.id.ShopEmailTV);
        ShopAddressTV = findViewById(R.id.ShopAddressTV);
        filterFoodBtn = findViewById(R.id.filterFoodBtn);
        filteredFoodTV = findViewById(R.id.filteredFoodTV);
        searchFoodsET = findViewById(R.id.searchFoodsET);
        foodsShowToClientRV = findViewById(R.id.foodsShowToClientRL);
        cartIV = findViewById(R.id.cartIV);


        shopUid= getIntent().getStringExtra("shopUid");
        firebaseAuth = FirebaseAuth.getInstance();

        backBtnShopDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





    }

}