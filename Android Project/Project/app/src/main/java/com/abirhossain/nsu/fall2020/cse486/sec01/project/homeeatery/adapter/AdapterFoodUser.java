package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.FilterFoodsUser;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.R;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.modelFood;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AdapterFoodUser extends RecyclerView.Adapter<AdapterFoodUser.HolderFoodUser> implements Filterable {

    private Context context;
    public ArrayList<modelFood> foodList,filterList;
    private FilterFoodsUser filter;

    public AdapterFoodUser(Context context, ArrayList<modelFood> foodList) {
        this.context = context;
        this.foodList = foodList;
        this.filterList = foodList;
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
                showQuantityDialog(ModelFood);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show food details

            }
        });


    }
    private double cost = 0, costTotal = 0;
    private int quantity = 0;

    private void showQuantityDialog(modelFood ModelFood) {
        //inflate the created layout for add to cart
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_quantity,null);
        //initialize layout views
        ImageView foodIcon = view.findViewById(R.id.foodIcon);
        TextView discountNoteTV = view.findViewById(R.id.discountNoteTV);
        TextView FoodTitleTV = view.findViewById(R.id.FoodTitleTV);
        TextView QuantityTV = view.findViewById(R.id.QuantityTV);
        TextView FoodDescTV = view.findViewById(R.id.FoodDescTV);
        TextView priceDiscounted = view.findViewById(R.id.priceDiscounted);
        TextView originalPrice = view.findViewById(R.id.originalPrice);
        TextView inc_button = view.findViewById(R.id.inc_button);
        TextView SelectedQuantityTV = view.findViewById(R.id.SelectedQuantityTV);
        TextView dec_button = view.findViewById(R.id.dec_button);
        TextView finalPriceTV = view.findViewById(R.id.finalPriceTV);
        Button addToCartDialogButton = view.findViewById(R.id.addToCartDialogButton);

        //get data from model
        String foodID = ModelFood.getFoodId();
        String title = ModelFood.getFoodTitle();
        String foodQuantity = ModelFood.getFoodQuantity();
        String foodDescription = ModelFood.getFoodDescription();
        String discountNote = ModelFood.getDiscountNote();
        String image = ModelFood.getFoodIcon();
        String Price;

        if (ModelFood.getDiscountAvailable().equals("true")){
            Price = ModelFood.getDiscountPrice();
            discountNoteTV.setVisibility(View.VISIBLE);
        }
        else {
            discountNoteTV.setVisibility(View.GONE);
            Price= ModelFood.getOriginalPrice();
        }

        cost = Double.parseDouble(Price.replaceAll("$",""));
        costTotal = Double.parseDouble(Price.replaceAll("$",""));
        quantity = 1;

        //dialog appear
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        try {
            Picasso.get().load(image).placeholder(R.drawable.additem_logo).into(foodIcon);

        }
        catch (Exception e){
           foodIcon.setImageResource(R.drawable.additem_logo);

        }

        FoodTitleTV.setText(title);
        QuantityTV.setText("Available: "+foodQuantity);
        FoodDescTV.setText(foodDescription);
        discountNoteTV.setText(discountNote);
        SelectedQuantityTV.setText(""+quantity);
        originalPrice.setText("Price: "+ModelFood.getOriginalPrice());
        priceDiscounted.setText("Discounted price: "+ModelFood.getDiscountPrice());
        finalPriceTV.setText("Total: "+costTotal+"$");
        AlertDialog dialog = builder.create();
        dialog.show();

        //increment decrement
        inc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                costTotal = costTotal + cost;
                quantity++;
                finalPriceTV.setText("Total "+costTotal);
                SelectedQuantityTV.setText(""+quantity);
            }
        });
        dec_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity>1){
                    costTotal = costTotal - cost;
                    quantity--;
                    finalPriceTV.setText("Total "+costTotal);
                    SelectedQuantityTV.setText(""+quantity);
                }
            }

        });
        addToCartDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = FoodTitleTV.getText().toString().trim();
                String priceEach = originalPrice.getText().toString().trim().replaceAll("$","");
                String price = finalPriceTV.getText().toString().trim().replaceAll("","");
                String quantity = SelectedQuantityTV.getText().toString().trim();
               addToCart(foodID,title,priceEach,price,quantity);
               dialog.dismiss();
            }
        });




    }

    private void addToCart(String foodID, String title, String priceEach, String price, String quantity) {

        
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null){
            filter= new FilterFoodsUser(this,filterList);
        }
        return filter;
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
