package com.abirhossain.nsu.fall2020.cse486.sec01.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.jar.Attributes;

public class ShowData extends AppCompatActivity {
    TextView Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        Name =(TextView)findViewById(R.id.text2);
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("user_email");
        Name.setText(email);
    }



    public void logout(View view) {
        Intent intent2= new Intent(ShowData.this,MainActivity.class);
        startActivity(intent2);

    }
}