package com.abirhossain.nsu.fall2020.cse486.sec01.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.abirhossain.nsu.fall2020.cse486.sec01.lab04.MainActivity.TEXT_REQUEST;

public class addData extends AppCompatActivity {
    String rice ="Rice", cake="Cake", butter= "Butter", cheese= "Cheese", lemon ="Lemon", carrot ="Carrot", ice="Ice-cream",bis="Biscuit";
    public static final String EXTRA_REPLY =
            "com.example.android.twoactivities.extra.REPLY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);


    }


    public void addItem1(View view) {
        Intent intent2 = new Intent();
        intent2.putExtra("Res",rice);
        setResult(RESULT_OK,intent2 );
        finish();

    }

    public void addItem2(View view) {
        Intent intent2 = new Intent();
        intent2.putExtra("Res",cake);
        setResult(RESULT_OK,intent2 );
        finish();

    }

    public void addItem3(View view) {
        Intent intent2 = new Intent();
        intent2.putExtra("Res",butter);
        setResult(RESULT_OK,intent2 );
        finish();


    }

    public void addItem4(View view) {
        Intent intent2 = new Intent();
        intent2.putExtra("Res",cheese);
        setResult(RESULT_OK,intent2 );
        finish();
    }

    public void addItem5(View view) {
        Intent intent2 = new Intent();
        intent2.putExtra("Res",lemon);
        setResult(RESULT_OK,intent2 );
        finish();
    }

    public void addItem6(View view) {

        Intent intent2 = new Intent();
        intent2.putExtra("Res",carrot);
        setResult(RESULT_OK,intent2 );
        finish();

    }


    public void addItem7(View view) {
        Intent intent2 = new Intent();
        intent2.putExtra("Res",ice);
        setResult(RESULT_OK,intent2 );
        finish();

    }

    public void addItem8(View view) {
        Intent intent2 = new Intent();
        intent2.putExtra("Res",bis);
        setResult(RESULT_OK,intent2 );
        finish();
    }



}