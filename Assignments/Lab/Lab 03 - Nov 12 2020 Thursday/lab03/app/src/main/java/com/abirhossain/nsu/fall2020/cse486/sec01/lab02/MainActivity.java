package com.abirhossain.nsu.fall2020.cse486.sec01.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText)findViewById(R.id.enter_email);
        password = (EditText)findViewById(R.id.editTextTextPassword);


    }

    public void login(View view) {
        String user_email = email.getText().toString();
        String user_pass = password.getText().toString();

        if(user_email.equals("abirhasan282@gmail.com") && user_pass.equals("123456")){
            Intent intent1= new Intent(MainActivity.this,ShowData.class);
            Bundle bundle= new Bundle();
            bundle.putString("user_email",user_email);
            intent1.putExtras(bundle);
            startActivity(intent1);
        }
        else{
            Toast toast = Toast.makeText(this,"Wrong Input ", Toast.LENGTH_LONG);
            toast.show();

        }


    }
}