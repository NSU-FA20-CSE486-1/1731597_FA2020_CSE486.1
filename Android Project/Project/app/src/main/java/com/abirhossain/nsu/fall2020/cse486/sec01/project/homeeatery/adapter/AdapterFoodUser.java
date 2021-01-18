package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.R;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.modelFood;

import java.util.ArrayList;


public class AdapterFoodUser extends RecyclerView.Adapter<AdapterFoodUser.HolderFoodUser> {

    private Context context;
    private ArrayList<modelFood> foodList;

    public AdapterFoodUser(Context context, ArrayList<modelFood> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public HolderFoodUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderFoodUser holder, int position) {

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class HolderFoodUser extends RecyclerView.ViewHolder{

        private ImageView foodIcon;
        private TextView discountNoteTV,FoodTitleTV,FoodDescTV,priceDiscounted,originalPrice;
        private Button addToCartButton;


        public HolderFoodUser(@NonNull View itemView) {
            super(itemView);
            foodIcon = itemView.findViewById(R.id.foodIcon);
            discountNoteTV = itemView.findViewById(R.id.discountNoteTV);
            FoodTitleTV = itemView.findViewById(R.id.FoodTitleTV);
            FoodDescTV = itemView.findViewById(R.id.FoodDescTV);
            priceDiscounted = itemView.findViewById(R.id.priceDiscounted);
            originalPrice = itemView.findViewById(R.id.originalPrice);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);


        }
    }
}
