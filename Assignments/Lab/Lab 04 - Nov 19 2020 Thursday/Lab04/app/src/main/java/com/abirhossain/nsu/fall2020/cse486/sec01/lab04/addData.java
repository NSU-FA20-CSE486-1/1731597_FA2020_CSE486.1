package com.abirhossain.nsu.fall2020.cse486.sec01.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.abirhossain.nsu.fall2020.cse486.sec01.lab04.MainActivity.TEXT_REQUEST;

public class addData extends AppCompatActivity {
    String rice ="Rice";
    public static final String EXTRA_REPLY =
            "com.example.android.twoactivities.extra.REPLY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_data);
        Button Item1 = findViewById(R.id.Rice);
        Item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent();
                intent2.putExtra("Rice",rice);
                setResult(RESULT_OK,intent2 );
                finish();
            }
        });

    }











}