package com.abirhossain.cse486.fall2020.quizz2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    public EditText eng_word,ben_word;
    public Button saveBtn,dictionaryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eng_word = findViewById(R.id.eng_word);
        ben_word = findViewById(R.id.ben_word);
        saveBtn = findViewById(R.id.saveBtn);
        dictionaryBtn = findViewById(R.id.dictionaryBtn);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chekInput();

            }
        });
        dictionaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent());
            }
        });


    }

    private void chekInput() {
        String word= eng_word.getText().toString();
        String  translate = ben_word.getText().toString();

        if(TextUtils.isEmpty(word)){
            Toast.makeText(MainActivity.this, "Enter english word", LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(translate)){
            Toast.makeText(MainActivity.this, "Enter bangla word", LENGTH_SHORT).show();


        }
        else{
            saveData(word,translate);

        }
    }

    private void saveData(String word, String translate)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("Dictionary").child(word).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("word", word);
                    userdataMap.put("translation", translate);


                    RootRef.child("Dictionary").child(word).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(MainActivity.this, "Word added successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {

                                        Toast.makeText(MainActivity.this, "addition unsuccessful", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Word already exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}