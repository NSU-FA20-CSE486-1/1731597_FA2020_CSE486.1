package com.abirhossain.nsu.fall2020.cse486.sec01.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class addData extends AppCompatActivity {
String messi;
    public static final String EXTRA_REPLY =
            "com.example.android.twoactivities.extra.REPLY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_data);

        messi="Messi";
    }

    public void Rice(View view) {
        Intent intent2= new Intent(addData.this,MainActivity.class);
        Bundle bundle= new Bundle();
        bundle.putString("p1",messi);
        intent2.putExtras(bundle);
        setResult(RESULT_OK, intent2);
        finish();


    }









    public void Biscutit(View view) {
    }

    public void Cake(View view) {
    }

    public void BUtter(View view) {
    }

    public void Cheese(View view) {
    }

    public void Lemon(View view) {
    }

    public void Carrot(View view) {
    }

    public void ice(View view) {
    }
}