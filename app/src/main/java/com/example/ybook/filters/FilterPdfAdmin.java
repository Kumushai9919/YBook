package com.example.ybook.filters;

import android.widget.Filter;

import com.example.ybook.adapters.AdapterCategory;
import com.example.ybook.adapters.AdapterPdfAdmin;
import com.example.ybook.models.ModelCategory;
import com.example.ybook.models.ModelPdf;

import java.util.ArrayList;


public class FilterPdfAdmin extends Filter {

    //arraylist in which we want to search
    ArrayList<ModelPdf> filterList;
    //adapter in which filter need to be implemented
    AdapterPdfAdmin adapterPdfAdmin;

    //constructor

    public FilterPdfAdmin(ArrayList<ModelPdf> filterList, AdapterPdfAdmin adapterPdfAdmin) {
        this.filterList = filterList;
        this.adapterPdfAdmin = adapterPdfAdmin;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();
        //value should not be null and empty
        if(charSequence != null && charSequence.length() > 0){

            //change to upper case, or lower case to avoid case sensitivity
            charSequence = charSequence.toString().toUpperCase();
            ArrayList<ModelPdf> filteredModels = new ArrayList<>();

            for(int i=0; i<filterList.size(); i++){
                //validate
                if(filterList.get(i).getTitle().toUpperCase().contains(charSequence)){
                    //add to filtered list
                    filteredModels.add(filterList.get(i));
                }
            }

            results.count = filteredModels.size();
            results.values = filteredModels;
        }
        else{
            results.count = filterList.size();
            results.values = filterList;
        }

        return results;  //don't miss it
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults results) {
        //apply filter changes
        adapterPdfAdmin.pdfArrayList = (ArrayList<ModelPdf>)results.values;

        //notify changes
        adapterPdfAdmin.notifyDataSetChanged();


    }
}
