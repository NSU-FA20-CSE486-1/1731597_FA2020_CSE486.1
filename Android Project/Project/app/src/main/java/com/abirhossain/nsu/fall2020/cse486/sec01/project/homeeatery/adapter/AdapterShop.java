package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.R;

public class AdapterShop {
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
