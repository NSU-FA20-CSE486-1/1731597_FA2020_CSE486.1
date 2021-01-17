package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter.AdapterShop;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.ModelShop;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainUserActivity extends AppCompatActivity {

    private TextView userName,clientEmailTV,ClientPhnTV,availableShopsTV,ClientOrderTV;
    private ImageView logOutBtn,client_image;
    private FirebaseAuth firebaseAuth;
    private RelativeLayout shopsShowToClient,ordersShowToClient;
    private RecyclerView shopsRV;

    private ArrayList<ModelShop> restaurants;
    private AdapterShop adapterRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        userName = findViewById(R.id.ClientName);
        logOutBtn = findViewById(R.id.Client_logout_btn);
        client_image = findViewById(R.id.client_image);
        clientEmailTV = findViewById(R.id.clientEmailTV);
        ClientPhnTV = findViewById(R.id.ClientPhnTV);
        availableShopsTV = findViewById(R.id.availableShopsTV);
        ClientOrderTV = findViewById(R.id.ClientOrderTV);
        shopsShowToClient = findViewById(R.id.shopsShowToClient);
        ordersShowToClient = findViewById(R.id.ordersShowToClient);
        shopsRV = findViewById(R.id.shopsRV);



        firebaseAuth = FirebaseAuth.getInstance();
        checkClient();
        showRestaurents();

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkClient();
            }
        });
        availableShopsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRestaurents();
            }
        });
        ClientOrderTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOrders();
            }
        });



    }

    private void showRestaurents(){
        //food ui showing and hiding order ui
        shopsShowToClient.setVisibility(View.VISIBLE);
        ordersShowToClient.setVisibility(View.GONE);

        availableShopsTV.setBackgroundResource(R.color.white);
        ClientOrderTV.setBackgroundResource(R.color.background);



    }



    private void userOrders() {
        //order ui showing and food order ui

        shopsShowToClient.setVisibility(View.GONE);
        ordersShowToClient.setVisibility(View.VISIBLE);

        ClientOrderTV.setBackgroundResource(R.color.white);
        availableShopsTV.setBackgroundResource(R.color.background);



    }


    private void checkClient() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user==null){
            startActivity(new Intent(MainUserActivity.this,LoginActivity.class));
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
                        //getting user data
                        for (DataSnapshot ds: snapshot.getChildren()){
                            String name = ""+ds.child("name").getValue();
                            String email = ""+ds.child("email").getValue();
                            String phone = ""+ds.child("phone").getValue();
                            String profileImage = ""+ds.child("profileImage").getValue();
                            String accountType = ""+ds.child("accountType").getValue();
                            String city = ""+ds.child("city").getValue();
                            //setting user data
                            userName.setText(name+"("+accountType+")");
                            clientEmailTV.setText(email);
                            ClientPhnTV.setText(phone);
                            try {
                                Picasso.get().load(profileImage).placeholder(R.drawable.ic_baseline_person_24).into(client_image);

                            }
                            catch (Exception e){
                                client_image.setImageResource(R.drawable.ic_baseline_person_24);
                            }
                            //load restaurants based on clients city
                            loadRestaurants(city);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void loadRestaurants(String ClientCity) {
        //initializing shop
        restaurants = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.orderByChild("accountType").equalTo("Seller")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        restaurants.clear();
                        for (DataSnapshot ds: snapshot.getChildren()){
                            ModelShop modelShop = ds.getValue(ModelShop.class);
                            String restaurantCity = ""+ds.child("city").getValue();
                            //check if restaurantCity and clientCity matches
                            if(restaurantCity.equals(ClientCity)){
                                restaurants.add(modelShop);
                            }
                            //for displaying all shops have to  skip if part from above and keep the restaurantLists.add(modelShop)
                            // restaurantLists.add(modelShop);
                        }
                        //adapter setup
                        adapterRestaurant = new AdapterShop(MainUserActivity.this,restaurants);
                        shopsRV.setAdapter(adapterRestaurant);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }


}