package com.abirhossain.cse486.fall2020.quizz2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowDataActivity extends AppCompatActivity {

    List<fetchData> Data;
    RecyclerView recyclerView;
    AdapterClass adapterClass;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        recyclerView = findViewById(R.id.show_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Data = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Dictionary");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    fetchData fdata = ds.getValue(fetchData.class);
                    Data.add(fdata);
                }
                adapterClass = new AdapterClass(Data);
                recyclerView.setAdapter(adapterClass);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}