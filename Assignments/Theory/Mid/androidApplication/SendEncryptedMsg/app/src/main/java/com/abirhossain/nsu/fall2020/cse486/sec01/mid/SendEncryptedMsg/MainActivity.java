package com.abirhossain.nsu.fall2020.cse486.sec01.mid.SendEncryptedMsg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText inputPhn, inputMsg, inputKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputPhn= findViewById(R.id.phnNumber);
        inputMsg=findViewById(R.id.msg);
        inputKey=findViewById(R.id.key);

    }
}