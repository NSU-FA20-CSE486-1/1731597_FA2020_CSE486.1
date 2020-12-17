package com.abirhossain.nsu.fall2020.cse486.sec01.lab08;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    int tabCount;

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount=behavior;


    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0: return new tabFragment1();
            case 1: return  new tabFragment2();
            case 2: return new tabFragment3();
            case 3: return  new tabFragment4();
            default: return null;

        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
