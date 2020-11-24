package com.abirhossain.nsu.fall2020.cse486.sec01.lab04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
   
    public static final int TEXT_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.text1);
        t2=(TextView)findViewById(R.id.text2);
        t3=(TextView)findViewById(R.id.text3);
        t4=(TextView)findViewById(R.id.text4);
        t5=(TextView)findViewById(R.id.text5);
        t6=(TextView)findViewById(R.id.text6);
        t7=(TextView)findViewById(R.id.text7);
        t8=(TextView)findViewById(R.id.text8);
        t9=(TextView)findViewById(R.id.text9);
        t10=(TextView)findViewById(R.id.text10);




    }

    public void  addItem()

    {
        Button addItem1 = findViewById(R.id.addItem);
        addItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, addData.class);
                startActivityForResult(intent1, TEXT_REQUEST);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==TEXT_REQUEST){
            if(resultCode==RESULT_OK){
                String rice= data.getStringExtra("Rice");
                t1.setText(rice);


            }

        }

    }
}