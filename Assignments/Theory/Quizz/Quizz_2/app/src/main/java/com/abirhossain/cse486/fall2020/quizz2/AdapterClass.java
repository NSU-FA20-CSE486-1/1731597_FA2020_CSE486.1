package com.abirhossain.cse486.fall2020.quizz2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter {

    List<fetchData> dataList;

    public AdapterClass(List<fetchData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.words_layout,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass viewHolderClass = (ViewHolderClass) holder;

        fetchData Fdata = dataList.get(position);
        viewHolderClass.show_eng_word.setText(Fdata.getEnglish_word());
        viewHolderClass.show_ben_word.setText(Fdata.getBengali_word());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView show_eng_word,show_ben_word;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            show_eng_word = itemView.findViewById(R.id.sEngWord);
            show_ben_word = itemView.findViewById(R.id.sBenWord);

        }
    }
}
