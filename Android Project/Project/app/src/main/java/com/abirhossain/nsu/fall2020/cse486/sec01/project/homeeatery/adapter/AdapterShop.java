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
