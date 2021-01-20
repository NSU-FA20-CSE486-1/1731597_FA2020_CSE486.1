package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.R;

public class AdapterCartItem {
    class HolderCartItem extends RecyclerView.ViewHolder{
        //ui views of row cart item
        private TextView ItemTitleTv,ItemPriceTv,ItemPriceEachTV,ItemQuantityTV,
                RemoveBtn;

        public HolderCartItem(@NonNull View itemView) {
            super(itemView);
            ItemTitleTv= itemView.findViewById(R.id.ItemTitleTv);
            ItemPriceTv= itemView.findViewById(R.id.ItemPriceTv);
            ItemPriceEachTV= itemView.findViewById(R.id.ItemPriceEachTV);
            ItemQuantityTV= itemView.findViewById(R.id.ItemQuantityTV);
            RemoveBtn= itemView.findViewById(R.id.RemoveBtn);

            
        }
    }
}
