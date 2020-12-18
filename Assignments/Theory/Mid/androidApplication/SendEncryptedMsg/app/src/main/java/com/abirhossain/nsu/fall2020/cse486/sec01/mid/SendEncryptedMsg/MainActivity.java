package com.abirhossain.nsu.fall2020.cse486.sec01.mid.SendEncryptedMsg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText inputPhn, inputMsg, inputKey;
    Button encButton;
    String outputString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputPhn= findViewById(R.id.phnNumber);
        inputMsg=findViewById(R.id.msg);
        inputKey=findViewById(R.id.key);
        encButton = findViewById(R.id.encBtn);

        encButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputString = encrypt(inputMsg.getText().toString(),inputKey.getText().toString());

            }
        });

    }

    private String encrypt(String encMsg, String encKey) {

        
    }


}