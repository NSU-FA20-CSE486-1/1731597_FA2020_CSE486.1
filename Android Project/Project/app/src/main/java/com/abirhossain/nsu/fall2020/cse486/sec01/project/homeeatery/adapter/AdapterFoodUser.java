package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.R;


public class AdapterFoodUser {
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
