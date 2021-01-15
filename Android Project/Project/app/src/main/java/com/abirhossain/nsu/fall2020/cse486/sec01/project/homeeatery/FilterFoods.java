package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery;

import android.widget.Filter;

import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.adapter.adapterProductClass;
import com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model.modelFood;

import java.util.ArrayList;

public class FilterFoods extends Filter {
    private adapterProductClass adapter;
    private ArrayList <modelFood> filterList;

    public FilterFoods(adapterProductClass adapter, ArrayList<modelFood> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //search query data checking
        if (constraint != null && constraint.length()>0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<modelFood>filteredModels = new ArrayList<>();
            for(int i = 0; i<filterList.size();i++){
                //checking search by title or category
                if (filterList.get(i).getFoodTitle().toUpperCase().contains(constraint)|| filterList.get(i).getFoodCategory().toUpperCase().contains(constraint)){
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count = filteredModels.size();
            results.values = filteredModels;
        }
        else {
            results.count = filterList.size();
            results.values = filterList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.foodList = (ArrayList<modelFood>) results.values;
        adapter.notifyDataSetChanged();

    }
}
