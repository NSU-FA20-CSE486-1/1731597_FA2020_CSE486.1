package com.abirhossain.nsu.fall2020.cse486.sec01.mid.SendEncryptedMsg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    EditText inputPhn, inputMsg, inputKey;
    Button encButton;
    String outputString, AES="AES",encKey,phnNumb;



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
                    phnNumb= inputPhn.getText().toString();
                    encKey =inputKey.getText().toString();
                    startNewActivity();




                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void startNewActivity() {

        Intent intent1= new Intent(MainActivity.this,EncryptedMsg.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("User_phn",phnNumb);
        bundle1.putString("User_key",encKey);
        bundle1.putString("Enc_msg",outputString);
        intent1.putExtras(bundle1);
        startActivity(intent1);

    }

    private String encrypt(String encMsg, String encKey) throws Exception {

        SecretKeySpec Key =  generateKey(encKey);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE,Key);
        byte [] encVal = cipher.doFinal(encMsg.getBytes());
        String encryptedValue= Base64.encodeToString(encVal,Base64.DEFAULT);
        return encryptedValue;


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