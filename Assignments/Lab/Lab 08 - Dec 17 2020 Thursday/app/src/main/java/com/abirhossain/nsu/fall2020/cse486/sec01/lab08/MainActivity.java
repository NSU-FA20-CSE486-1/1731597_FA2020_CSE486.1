package com.abirhossain.nsu.fall2020.cse486.sec01.lab08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem TabItem1,TabItem2,TabItem3,TabItem4;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout1);
        TabItem1= findViewById(R.id.tab1);
        TabItem2= findViewById(R.id.tab2);
        TabItem3= findViewById(R.id.tab3);
        TabItem4= findViewById(R.id.tab4);

    }
}