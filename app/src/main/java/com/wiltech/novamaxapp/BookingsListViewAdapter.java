package com.wiltech.novamaxapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Wiliam on 25/02/2015.
 */
public class BookingsListViewAdapter extends ArrayAdapter<String> {

    public BookingsListViewAdapter(Context context, String[] bookings) {
        super(context, R.layout.custom_bookings_row, bookings);
    }

    //include method to tell where you want to add the custom row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the background information
        LayoutInflater wilsInflater = LayoutInflater.from(getContext());
        //create one custom row
        View customView = wilsInflater.inflate(R.layout.custom_bookings_row, parent, false);

        //get  a reference to everything - String, text and image from custom_row.xml
        String singleBookingItem = getItem(position);
        TextView lblBookingsToday = (TextView) customView.findViewById(R.id.lblBookingsToday);

        lblBookingsToday.setText(singleBookingItem);

        //return the view
        return customView;
    }
}
