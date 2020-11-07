package com.abirhossain.nsu.fall2020.cse486.sec01.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {

        EditText email = (EditText)findViewById(R.id.enter_email);
        EditText password = (EditText)findViewById(R.id.editTextTextPassword);

        String user_email = email.getText().toString();
        String user_pass = password.getText().toString();

        Toast toast = Toast.makeText(this,"User Email: "+user_email+"\n User Password: "+user_pass, Toast.LENGTH_LONG);
        toast.show();


    }
}