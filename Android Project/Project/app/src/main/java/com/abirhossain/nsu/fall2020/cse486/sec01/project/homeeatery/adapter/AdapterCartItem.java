package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.R;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.ModelCartItem;

import java.util.ArrayList;

public class AdapterCartItem extends RecyclerView.Adapter<AdapterCartItem.HolderCartItem> {
    private Context context;
    private ArrayList<ModelCartItem> cartItems;

    public AdapterCartItem(Context context, ArrayList<ModelCartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public HolderCartItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCartItem holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

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
