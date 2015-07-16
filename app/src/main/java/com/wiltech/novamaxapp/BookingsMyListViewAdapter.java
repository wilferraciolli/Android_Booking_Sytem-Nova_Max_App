package com.wiltech.novamaxapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Wiliam on 01/03/2015.
 */
public class BookingsMyListViewAdapter extends ArrayAdapter<String>{


    //constructor
    public BookingsMyListViewAdapter(Context context,String[] bookingDetails) {
       super(context, R.layout.custom_bookings_my_row, bookingDetails);
    }

    //include method to tell where you want to add the custom row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the background information
        LayoutInflater wilsInflater = LayoutInflater.from(getContext());
        //create one custom row
        View customView = wilsInflater.inflate(R.layout.custom_bookings_my_row, parent, false);

        //get  a reference to everything - String,
        String bookings = getItem(position);
        TextView txtBookingDetails = (TextView) customView.findViewById(R.id.txtBookingDetails);

        //set the text field
        txtBookingDetails.setText(bookings);

        //return the view
        return customView;
    }
}
