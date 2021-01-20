package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.R;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.ModelCartItem;

import java.util.ArrayList;

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;

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
        //inflating row_cartItems.xml layout
        View view = LayoutInflater.from(context).inflate(R.layout.row_cartitem,parent,false);
        return new HolderCartItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCartItem holder, int position) {
        //get data
        ModelCartItem modelCartItem = cartItems.get(position);
        String id = modelCartItem.getId();
        String pid = modelCartItem.getPid();
        String title = modelCartItem.getName();
        String cost = modelCartItem.getCost();
        String price = modelCartItem.getPrice();
        String quantity = modelCartItem.getQuantity();

        //setData
        holder.ItemTitleTv.setText(title);
        holder.ItemPriceEachTV.setText(cost);
        holder.ItemQuantityTV.setText(quantity);
        holder.ItemPriceTv.setText(price);

        //remove click hanlde

        holder.RemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyDB easyDB = EasyDB.init(context,"ITEMS_DB")
                        .setTableName("ITEMS_TABLE")
                        .addColumn(new Column("Item_Id",new String[]{"text","unique"}))
                        .addColumn(new Column("Item_PId",new String[]{"text","not null"}))
                        .addColumn(new Column("Item_Name",new String[]{"text","not null"}))
                        .addColumn(new Column("Item_Price_Each",new String[]{"text","not null"}))
                        .addColumn(new Column("Item_Price",new String[]{"text","not null"}))
                        .addColumn(new Column("Item_Quantity",new String[]{"text","not null"}))
                        .doneTableColumn();
                easyDB.deleteRow(1,id);
                Toast.makeText(context, "item deleted", Toast.LENGTH_SHORT).show();
                //refreshing list
                cartItems.remove(position);
                notifyItemChanged(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
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
