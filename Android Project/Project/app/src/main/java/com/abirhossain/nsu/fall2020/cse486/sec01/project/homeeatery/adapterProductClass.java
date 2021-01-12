package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.transition.Hold;

import java.util.ArrayList;

public class adapterProductClass extends RecyclerView.Adapter<adapterProductClass.HolderProductSeller>{

    private Context context;
    public ArrayList<modelFood> foodList;

    public adapterProductClass(Context context, ArrayList<modelFood> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate Layout
        View view = LayoutInflater.from(context).inflate(R.layout.row_food_seller,parent,false);
        return new HolderProductSeller(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductSeller holder, int position) {
        //getting data
        modelFood ModelFood = foodList.get(position);
        String id = ModelFood.getFoodId();
        String uid = ModelFood.getUid();
        String discountAvailable = ModelFood.getDiscountAvailable();
        String discountNote = ModelFood.getDiscountNote();
        String discountPrice = ModelFood.getDiscountPrice();
        String foodCategory = ModelFood.getFoodCategory();
        String foodDescription = ModelFood.getFoodDescription();
        String foodIcon = ModelFood.getFoodIcon();
        String foodQuantity = ModelFood.getFoodQuantity();
        String title = ModelFood.getFoodTitle();
        String timeStamp = ModelFood.getTimestamp();
        String OriginalPrice = ModelFood.getOriginalPrice();

        //setting data
        holder.titleTV.setText(title);
        holder.FoodQuantityTV.setText(foodQuantity);
        holder.priceDiscounted.setText("$"+discountPrice);
        holder.discountNote.setText(discountNote);
        holder.originalPrice.setText("$"+OriginalPrice);
        if(discountAvailable.equals("true")){
            //product with discount
            holder.priceDiscounted.setVisibility(View.VISIBLE);
            holder.discountNote.setVisibility(View.VISIBLE);

        }
        else {
            //product without discount
            holder.priceDiscounted.setVisibility(View.GONE);
            holder.discountNote.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class HolderProductSeller extends RecyclerView.ViewHolder{
        /*holds views of recyclerView*/
        private ImageView foodIconIV;
        private TextView discountNote,titleTV,priceDiscounted,originalPrice,FoodQuantityTV;


        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);
            foodIconIV = itemView.findViewById(R.id.foodIcon);
            discountNote = itemView.findViewById(R.id.discountNoteTV);
            titleTV = itemView.findViewById(R.id.FoodTitleTV);
            priceDiscounted = itemView.findViewById(R.id.priceDiscounted);
            originalPrice = itemView.findViewById(R.id.originalPrice);
            FoodQuantityTV = itemView.findViewById(R.id.FoodQuantityTV);




        }
    }
}
