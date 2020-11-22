package com.abirhossain.nsu.fall2020.cse486.sec01.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    String p1;
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

    public void add(View view) {

        Intent intent1= new Intent(MainActivity.this,addData.class);
        startActivityForResult(intent1, TEXT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                //String reply = data.getStringExtra(addData.EXTRA_REPLY);

                // Make the reply head visible.
//                t1.setVisibility(View.VISIBLE);

                // Set the reply and make it visible.
                //t1.setText(p1);
                //t1.setVisibility(View.VISIBLE);

                Bundle bundle = getIntent().getExtras();
                String email = bundle.getString("p1");
              //  mReplyTextView.setVisibility(View.VISIBLE);
                t1.setText(email);
            }
        }
    }


}