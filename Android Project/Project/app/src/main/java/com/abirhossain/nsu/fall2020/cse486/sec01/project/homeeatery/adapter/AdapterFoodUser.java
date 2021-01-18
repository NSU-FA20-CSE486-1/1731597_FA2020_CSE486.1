package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.R;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.modelFood;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AdapterFoodUser extends RecyclerView.Adapter<AdapterFoodUser.HolderFoodUser> {

    private Context context;
    public ArrayList<modelFood> foodList;

    public AdapterFoodUser(Context context, ArrayList<modelFood> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public HolderFoodUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating layout
        View view = LayoutInflater.from(context).inflate(R.layout.row_food_user,parent,false);
        return new HolderFoodUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderFoodUser holder, int position) {

        //getting data
        modelFood ModelFood = foodList.get(position);
        String discountAvailable = ModelFood.getDiscountAvailable();
        String discountNote = ModelFood.getDiscountNote();
        String discountPrice = ModelFood.getDiscountPrice();
        String foodCategory = ModelFood.getFoodCategory();
        String originalPrice = ModelFood.getOriginalPrice();
        String foodDescription = ModelFood.getFoodDescription();
        String foodTitle = ModelFood.getFoodTitle();
        String foodQuantity = ModelFood.getFoodQuantity();
        String foodId = ModelFood.getFoodId();
        String timeStamp = ModelFood.getTimestamp();
        String foodIcon = ModelFood.getFoodIcon();

        //setting data
        holder.FoodTitleTV.setText(foodTitle);
        holder.discountNoteTV.setText(discountNote);
        holder.FoodDescTV.setText(foodDescription);
        holder.originalPrice.setText("$"+originalPrice);
        holder.priceDiscounted.setText("$"+discountPrice);
        if(discountAvailable.equals("true")){
            //product with discount
            holder.priceDiscounted.setVisibility(View.VISIBLE);
            holder.discountNoteTV.setVisibility(View.VISIBLE);

        }
        else {
            //product without discount
            holder.priceDiscounted.setVisibility(View.GONE);
            holder.discountNoteTV.setVisibility(View.GONE);
        }
        try {
            Picasso.get().load(foodIcon).placeholder(R.drawable.additem_logo).into(holder.foodIcon);

        }
        catch (Exception e){
            holder.foodIcon.setImageResource(R.drawable.additem_logo);

        }
        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add to cart
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show food details

            }
        });


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
