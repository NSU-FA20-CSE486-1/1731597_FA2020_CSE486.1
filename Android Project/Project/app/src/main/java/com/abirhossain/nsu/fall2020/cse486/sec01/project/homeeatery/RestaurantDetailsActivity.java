package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter.AdapterCartItem;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter.AdapterFoodUser;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.ModelCartItem;
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

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private ImageView shopImage,backBtnShopDetails,callRestaurant,filterFoodBtn,cartIV,openMapIV;
    private TextView  ShopNameTV,shopStatusTV,feeTV,ShopEmailTV,ShopPhoneTV,ShopAddressTV,
            filteredFoodTV;
    private EditText searchFoodsET;
    private RecyclerView foodsShowToClientRV;
    private String shopUid,shopName,shopPhone,shopEmail,shopAddress,shopLatitude,shopLongitude
            ,userLatitude,userLongitude;
    private FirebaseAuth firebaseAuth;
    private ArrayList<modelFood> foodList;
    private AdapterFoodUser adapterFoodUser;
    //cart
    private ArrayList<ModelCartItem> cartItemList;
    private AdapterCartItem adapterCartItem;


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
        callRestaurant = findViewById(R.id.callRestaurant);
        filterFoodBtn = findViewById(R.id.filterFoodBtn);
        openMapIV = findViewById(R.id.openMapIV);



        shopUid= getIntent().getStringExtra("shopUid");
        firebaseAuth = FirebaseAuth.getInstance();

        loadMyInfo();
        loadRestaurantDetails();
        loadRestaurantFoods();
        //search foods
        searchFoodsET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {


                }
                catch (Exception e){
                    e.printStackTrace();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        backBtnShopDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        cartIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show cart dialog
                showCartDialog();

            }
        });
        callRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calltoRestaurant();
            }
        });
        openMapIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        filterFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantDetailsActivity.this);
                builder.setTitle("Choose food category")
                        .setItems(Constants.FoodCategory1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //get searched food
                                String selectedFood = Constants.FoodCategory1[which];
                                filteredFoodTV.setText(selectedFood);
                                if (selectedFood.equals("All")){
                                    //load all foods
                                    loadRestaurantFoods();
                                }
                                else
                                {
                                    adapterFoodUser.getFilter().filter(selectedFood);
                                }

                            }
                        }).show();

            }
        });






    }

    public double allTotalCost = 0.0;
    public TextView subTotalTv,dFeeTv,TotalCostTv;

    private void showCartDialog() {

        //init array list
        cartItemList = new ArrayList<>();


        //inflate cart layout
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_cart,null);
        //init ui
        TextView ShowShopNameTV, subTotalTextTv;
        RecyclerView cartItemsRV;
        Button orderNowBtn;
        ShowShopNameTV = findViewById(R.id.ShowShopNameTV);
        subTotalTv = findViewById(R.id.subTotalTv);
        dFeeTv = findViewById(R.id.dFeeTv);
        TotalCostTv = findViewById(R.id.TotalCostTv);
        cartItemsRV = findViewById(R.id.cartItemsRV);
        orderNowBtn = findViewById(R.id.orderNowBtn);

        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //setting view to dialog
        builder.setView(view);
        ShowShopNameTV.setText(shopName);
        EasyDB easyDB = EasyDB.init(this,"ITEMS_DB")
                .setTableName("ITEMS_TABLE")
                .addColumn(new Column("Item_Id",new String[]{"text","unique"}))
                .addColumn(new Column("Item_PId",new String[]{"text","not null"}))
                .addColumn(new Column("Item_Name",new String[]{"text","not null"}))
                .addColumn(new Column("Item_Price_Each",new String[]{"text","not null"}))
                .addColumn(new Column("Item_Price",new String[]{"text","not null"}))
                .addColumn(new Column("Item_Quantity",new String[]{"text","not null"}))
                .doneTableColumn();
        //getting records from db
        Cursor res = easyDB.getAllData();
        while (res.moveToNext()){
            String id = res.getString(1);
            String pid = res.getString(2);
            String name = res.getString(3);
            String price = res.getString(4);
            String cost = res.getString(5);
            String quantity = res.getString(6);

            allTotalCost = allTotalCost = Double.parseDouble(cost);
            ModelCartItem modelCartItem = new ModelCartItem(
                    ""+id,
                    ""+pid,
                    ""+name,
                    ""+price,
                    ""+cost,
                    ""+quantity
            );
            cartItemList.add(modelCartItem);
        }
   

    }

    private void openMap() {
        //saddr= source address
        String Address = "https://maps.google.com/maps?saddr="+userLatitude+","+userLongitude+"&daddr="+shopLatitude+","+shopLongitude;
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(Address));
        startActivity(intent);
    }

    private void calltoRestaurant() {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+Uri.encode(shopPhone))));
    }

    private void loadMyInfo() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.orderByChild("uid").equalTo(firebaseAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //getting user data
                        for (DataSnapshot ds: snapshot.getChildren()){
                            String name = ""+ds.child("name").getValue();
                            String email = ""+ds.child("email").getValue();
                            String phone = ""+ds.child("phone").getValue();
                            String profileImage = ""+ds.child("profileImage").getValue();
                            String accountType = ""+ds.child("accountType").getValue();
                            String city = ""+ds.child("city").getValue();
                            userLatitude = ""+ds.child("latitude").getValue();
                            userLongitude = ""+ds.child("longitude").getValue();


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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
                shopAddress = ""+ds.child("address").getValue();
                String deliveryFee = ""+ds.child("deliveryFee").getValue();
                String profileImage = ""+ds.child("profileImage").getValue();
                String shopOpen = ""+ds.child("shopOpen").getValue();

                //set Shop data
                ShopNameTV.setText(shopName);
                ShopEmailTV.setText(shopEmail);
                ShopPhoneTV.setText(shopPhone);
                ShopAddressTV.setText(shopAddress);
                feeTV.setText("Delivery Fee: "+deliveryFee);
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
        //init list
        foodList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(shopUid).child("Foods")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //clear list before adding item
                        foodList.clear();
                        for (DataSnapshot ds: snapshot.getChildren()){
                            modelFood ModelFood = ds.getValue(modelFood.class);
                            foodList.add(ModelFood);
                        }
                        //adapter setup
                        adapterFoodUser = new AdapterFoodUser(RestaurantDetailsActivity.this,foodList);
                        foodsShowToClientRV.setAdapter(adapterFoodUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }

}