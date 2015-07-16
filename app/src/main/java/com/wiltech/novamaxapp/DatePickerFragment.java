package com.wiltech.novamaxapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;
//import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by Wiliam on 25/02/2015.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //define the initial date
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }



    //Create the DatePicker
    //DatePickerDialog datePicker = new DatePickerDialog(getActivity(), this, year, month, day);

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String suffix = getString(dayOfMonth);
        Toast.makeText(getActivity(),
                String.valueOf(dayOfMonth) + suffix + " "
        + String.valueOf(monthOfYear) + " "
        + String.valueOf(year), Toast.LENGTH_LONG).show();

    }
}
