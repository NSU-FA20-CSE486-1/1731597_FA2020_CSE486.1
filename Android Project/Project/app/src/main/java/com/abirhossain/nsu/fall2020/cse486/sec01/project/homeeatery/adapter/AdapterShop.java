package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.R;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.ModelShop;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.modelFood;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterShop extends RecyclerView.Adapter<AdapterShop.HolderShop> {
    private Context context;
    private ArrayList<ModelShop> shopList;

    public AdapterShop(Context context, ArrayList<ModelShop> shopList) {
        this.context = context;
        this.shopList = shopList;
    }

    @NonNull
    @Override
    public HolderShop onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.row_shop,parent,false);
        return new HolderShop(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderShop holder, int position) {
        //getting data
        ModelShop modelShop = shopList.get(position);
        String accountType =  modelShop.getAccountType();
        String address =  modelShop.getAddress();
        String city = modelShop.getCity();
        String country = modelShop.getCountry();
        String deliveryFee = modelShop.getDeliveryFee();
        String email = modelShop.getEmail();
        String latitude = modelShop.getLatitude();
        String longitude = modelShop.getLongitude();
        String online = modelShop.getOnline();
        String name = modelShop.getName();
        String phone = modelShop.getPhone();
        String uid = modelShop.getUid();
        String timestamp = modelShop.getTimestamp();
        String shopOpen = modelShop.getShopOpen();
        String state = modelShop.getState();
        String profileImage = modelShop.getProfileImage();
        String shopName = modelShop.getShopName();

        //setting data
        holder.ShopNameTV.setText(shopName);
        holder.ShopPhoneTV.setText(phone);
        holder.ShopAddressTV.setText(address);
        //check if online or offline restaurant
        if (online.equals("true")){
            holder.online_offline_IV.setVisibility(View.VISIBLE);
        }
        else{
            holder.online_offline_IV.setVisibility(View.GONE);
        }
        //check if shop is open or not
        if (shopOpen.equals("true")){
            holder.shopStatusTV.setVisibility(View.GONE);
        } else{
            holder.shopStatusTV.setVisibility(View.VISIBLE);
        }
        try {
            Picasso.get().load(profileImage).placeholder(R.drawable.ic_baseline_home_24).into(holder.shopIv);
        }
        catch (Exception e){
            holder.shopIv.setImageResource(R.drawable.ic_baseline_home_24);
        }


    }

    @Override
    public int getItemCount() {
        return shopList.size(); //number of shops
    }

    //view Holder
    class HolderShop extends RecyclerView.ViewHolder{

        //ui initializing of row_shop.xml
        private ImageView shopIv,online_offline_IV;
        private TextView shopStatusTV,ShopNameTV,ShopPhoneTV,ShopAddressTV;
        private RatingBar RatingBar;

        public HolderShop(@NonNull View itemView) {
            super(itemView);
            shopIv = itemView.findViewById(R.id.shopIv);
            online_offline_IV = itemView.findViewById(R.id.online_offline_IV);
            shopStatusTV = itemView.findViewById(R.id.shopStatusTV);
            ShopNameTV = itemView.findViewById(R.id.ShopNameTV);
            ShopPhoneTV = itemView.findViewById(R.id.ShopPhoneTV);
            ShopAddressTV = itemView.findViewById(R.id.ShopAddressTV);
            RatingBar = itemView.findViewById(R.id.RatingBar);
            shopIv = itemView.findViewById(R.id.shopIv);
        }
    }
}
