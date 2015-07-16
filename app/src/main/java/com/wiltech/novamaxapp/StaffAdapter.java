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
public class StaffAdapter extends ArrayAdapter<String>{

    //declare the array to old the images path
    private final Integer[] imageID;
    //constructor
    public StaffAdapter(Context context, String[] names, Integer[] imageIDs) {
        super(context, R.layout.staff_row, names);

        //create the image object to read its path
        this.imageID = imageIDs;
    }//ends constructor


    //include the method to tell where you want to add the row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the background information
        LayoutInflater staffInflater = LayoutInflater.from(getContext());

        //create one custom row
        View customView = staffInflater.inflate(R.layout.staff_row, parent, false);

        //get reference to everything String, text and image from services_row.sml
        String staffName = getItem(position);
        TextView lblStaffName = (TextView)customView.findViewById(R.id.lblServiceName);
        ImageView imgVStaff = (ImageView)customView.findViewById(R.id.imgVServiceImage);

        //set the label and image of each element on the list
        lblStaffName.setText(staffName);
        imgVStaff.setImageResource(imageID[position]);

        //imgVServiceImage.setImageResource(R.drawable.ic_launcher);

        //return the customized view
        return customView;

    }
}
