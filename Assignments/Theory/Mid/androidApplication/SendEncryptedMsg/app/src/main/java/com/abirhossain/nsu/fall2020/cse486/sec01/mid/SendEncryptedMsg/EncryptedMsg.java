package com.abirhossain.nsu.fall2020.cse486.sec01.mid.SendEncryptedMsg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EncryptedMsg extends AppCompatActivity {
    TextView sPhn,EncMsg, EncKey;
    Button sendBtn;
    String phnNumb,encMsg,encKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypted_msg);
        sPhn=findViewById(R.id.sPhnNumb);
        EncMsg = findViewById(R.id.sEncMsg);
        EncKey = findViewById(R.id.sKey);
        sendBtn =findViewById(R.id.sMsg);

        Bundle bundle1= getIntent().getExtras();
        phnNumb = bundle1.getString("User_phn");
        encMsg=bundle1.getString("Enc_msg");
        encKey=bundle1.getString("User_key");


        sPhn.setText("Phone Number : "+phnNumb);
        EncMsg.setText("Encrypted Message: "+encMsg);
        EncKey.setText("Encryption Key: "+encKey);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto: "+sPhn);
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Encrypted Message "+encMsg+ "Key: "+encKey);
                startActivity(intent);
            }
        });






    }
}