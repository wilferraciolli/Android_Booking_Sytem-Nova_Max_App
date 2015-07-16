package com.wiltech.novamaxapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Wiliam on 18/02/2015.
 */
public class ServicesAdapter extends ArrayAdapter<String>{

    //declare the array to old the images path
    private final Integer[] imageID;
    //constructor
    public ServicesAdapter(Context context, String[] services, Integer[] imageIDs) {
        super(context, R.layout.services_row, services);

        this.imageID = imageIDs;
    }

    //include the method to tell where you want to add the row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the background information
        LayoutInflater servicesInflater = LayoutInflater.from(getContext());

        //create one custom row
        View customView = servicesInflater.inflate(R.layout.services_row, parent, false);

        //get reference to everything String, text and image from services_row.sml
        String serviceName = getItem(position);
        TextView lblServiceName = (TextView)customView.findViewById(R.id.lblServiceName);
        ImageView imgVServiceImage = (ImageView)customView.findViewById(R.id.imgVServiceImage);

        //set the label and image of each element on the list
        lblServiceName.setText(serviceName);
        imgVServiceImage.setImageResource(imageID[position]);

        //imgVServiceImage.setImageResource(R.drawable.ic_launcher);

        //return the customized view
        return customView;
    }
}
