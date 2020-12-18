package com.abirhossain.nsu.fall2020.cse486.sec01.mid.SendEncryptedMsg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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
                try {
                    outputString = encrypt(inputMsg.getText().toString(),inputKey.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private String encrypt(String encMsg, String encKey) throws Exception {

        SecretKeySpec Key =  generateKey(encKey);


    }

    private SecretKeySpec generateKey(String encKey) throws Exception {

        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte [] bytes = encKey.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return secretKeySpec;


    }


}