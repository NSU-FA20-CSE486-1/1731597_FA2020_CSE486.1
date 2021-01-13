package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainSellerActivity extends AppCompatActivity {
    private TextView vendorName,ShopNameTV,ShopEmailTV,availableFoodTV,orderTV,filteredFoodTV;
    private ImageView logOutBtn, AddProductBtn,sellerImage,filterFoodBtn;
    private FirebaseAuth firebaseAuth;
    private RelativeLayout foodsShowToSeller,ordersShowToSeller;
    private EditText searchFoodsET;
    private RecyclerView ShowFoodRecyclerView;
    private ArrayList<modelFood> foodList;
    private adapterProductClass adapterFoodSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);
        vendorName = findViewById(R.id.SellerNameTV);
        logOutBtn = findViewById(R.id.Seller_logout_btn);
        AddProductBtn = findViewById(R.id.Seller_add_btn);
        sellerImage = findViewById(R.id.seller_image);
        ShopNameTV = findViewById(R.id.ShopNameTV);
        ShopEmailTV = findViewById(R.id.ShopEmailTV);
        availableFoodTV= findViewById(R.id.availableFoodTV);
        orderTV = findViewById(R.id.orderTV);
        ordersShowToSeller = findViewById(R.id.ordersShowToSeller);
        foodsShowToSeller = findViewById(R.id.foodsShowToSeller);
        filteredFoodTV = findViewById(R.id.filteredFoodTV);
        searchFoodsET = findViewById(R.id.searchFoodsET);
        filterFoodBtn = findViewById(R.id.filterFoodBtn);
        ShowFoodRecyclerView = findViewById(R.id.ShowFoodRecyclerView);



        firebaseAuth = FirebaseAuth.getInstance();
        checkVendor();
        LoadAllFoods();
        //after opening app show the products available
        showFoodsUI();

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkVendor();
            }
        });

        AddProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open add product activity
                startActivity(new Intent(MainSellerActivity.this,AddProductActivity.class));
            }
        });
        availableFoodTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show foods
                showFoodsUI();

            }
        });
        orderTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show orders
                showOrdersUI();

            }
        });
        filterFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainSellerActivity.this);
                builder.setTitle("Choose food category")
                        .setItems(Constants.FoodCategory, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
            }
        });

    }

    private void LoadAllFoods() {

        foodList = new ArrayList<>();

        //get all foods
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(firebaseAuth.getUid()).child("Foods")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //clear the list
                        foodList.clear();
                        for (DataSnapshot ds: snapshot.getChildren()){
                            modelFood ModelFood = ds.getValue(modelFood.class);
                            foodList.add(ModelFood);

                        }
                        //adapter setup
                        adapterFoodSeller = new adapterProductClass(MainSellerActivity.this,foodList);
                        ShowFoodRecyclerView.setAdapter(adapterFoodSeller);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                })


    }

    private void showFoodsUI() {
        //food ui showing and hiding order ui
        foodsShowToSeller.setVisibility(View.VISIBLE);
        ordersShowToSeller.setVisibility(View.GONE);

        availableFoodTV.setBackgroundResource(R.color.white);
        orderTV.setBackgroundResource(R.color.background);



    }


    private void showOrdersUI() {
        //order ui showing and food order ui

        foodsShowToSeller.setVisibility(View.GONE);
        ordersShowToSeller.setVisibility(View.VISIBLE);

        orderTV.setBackgroundResource(R.color.white);
        availableFoodTV.setBackgroundResource(R.color.background);



    }



    private void checkVendor() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user==null){
            startActivity(new Intent(MainSellerActivity.this,LoginActivity.class));
            finish();
        }
        else
        {
           loadInfo();
        }
    }

    private void loadInfo() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.orderByChild("uid").equalTo(firebaseAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds: snapshot.getChildren()){
                            String name = ""+ds.child("name").getValue();
                            String accountType = ""+ds.child("accountType").getValue();
                            String email = ""+ds.child("email").getValue();
                            String shopName = ""+ds.child("shopName").getValue();
                            String profileImage = ""+ds.child("profileImage").getValue();
                            vendorName.setText(name+"("+accountType+")");
                            ShopNameTV.setText(shopName);
                            ShopEmailTV.setText(email);
                            try {
                                Picasso.get().load(profileImage).placeholder(R.drawable.ic_baseline_person_24).into(sellerImage);
                            }
                            catch (Exception e){
                                sellerImage.setImageResource(R.drawable.ic_baseline_person_24);

                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}