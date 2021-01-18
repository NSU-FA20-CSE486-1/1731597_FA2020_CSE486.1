package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.ModelShop;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.modelFood;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private ImageView shopImage,backBtnShopDetails,callRestaurant,filterFoodBtn,cartIV;
    private TextView  ShopNameTV,shopStatusTV,feeTV,ShopEmailTV,ShopPhoneTV,ShopAddressTV,
            filteredFoodTV;
    private EditText searchFoodsET;
    private RecyclerView foodsShowToClientRV;
    private String shopUid,shopName,shopPhone,shopEmail,shopAddress,shopLatitude,shopLongitude;
    private FirebaseAuth firebaseAuth;
    private ArrayList<modelFood> foodList;

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

        loadMyInfo();
        loadRestaurantDetails();
        loadRestaurantFoods();

        backBtnShopDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        cartIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





    }

    private void loadMyInfo() {
    }

    private void loadRestaurantDetails() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(shopUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                //getting shop data
                shopName = ""+ds.child("name").getValue();
                shopEmail = ""+ds.child("email").getValue();
                shopPhone = ""+ds.child("phone").getValue();
                shopLatitude= ""+ds.child("latitude").getValue();
                shopLongitude = ""+ds.child("longitude").getValue();
                String deliveryFee = ""+ds.child("deliveryFee").getValue();
                String profileImage = ""+ds.child("profileImage").getValue();
                String shopOpen = ""+ds.child("shopOpen").getValue();

                //set Shop data
                ShopNameTV.setText(shopName);
                ShopEmailTV.setText(shopEmail);
                ShopPhoneTV.setText(shopPhone);
                ShopAddressTV.setText(shopAddress);
                feeTV.setText(deliveryFee);
                if (shopOpen.equals("true")){
                    shopStatusTV.setText("Open");
                }
                else {
                    shopStatusTV.setText("Closed");
                }
                try {
                    Picasso.get().load(profileImage).placeholder(R.color.greenish).into(shopImage);

                }
                catch (Exception e){
                    shopImage.setImageResource(R.color.greenish);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadRestaurantFoods() {
    }

}