package com.wiltech.novamaxapp;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class AppointmentsActivity extends ActionBarActivity {

    //Globals
    Context context = this;

    String date = "";
    String userName = "";
    String staffName = "";
    String service = "";
    String timeIn = "";
    String timeOut = "";
    int price = 10;

    EditText txtDateAppoint;
    EditText txtUserNameAppointment;
    Spinner spnTime;
    Spinner spnService;
    Spinner spnStaff;

    //Database handler
    DBBookingsHandler dbBookingsHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        //******************** check if the user is logged on **************
        if (SaveUserLoggedInSharedPreference.getPrefUserName(AppointmentsActivity.this).length() == 0) {
            //send to main activity to log in
            Toast.makeText(getApplicationContext(), "Please Log In", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(mainIntent);
        } else {
            //get the user name
            userName = SaveUserLoggedInSharedPreference.getPrefUserName(AppointmentsActivity.this);
        }


        //get the date chosen from the intent
        Intent dataIntent = getIntent();
        //get the extras variable put to it
        date = dataIntent.getExtras().getString("date");

        //Create the database handler
        dbBookingsHandler = new DBBookingsHandler(this, null, null, 1);

        //Set the interface
        txtDateAppoint = (EditText) findViewById(R.id.txtDateAppointment);
        //set the date
        txtDateAppoint.setText(date);
        //set the username
        userName = SaveUserLoggedInSharedPreference.getPrefUserName(AppointmentsActivity.this);
        txtUserNameAppointment = (EditText) findViewById(R.id.txtUserNameAppointment);
        txtUserNameAppointment.setText(userName);

        //Add items to the spinner
        fiilSpinners();

        //Set listener View if user taps on date editText

    }//ends on create

    //************************* method to populate all the spinners
    public void fiilSpinners() {
        spnTime = (Spinner) findViewById(R.id.spnTime);
        String[] times = new String[]{"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        ArrayAdapter<String> adapterTimes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, times);
        spnTime.setAdapter(adapterTimes);
        spnStaff = (Spinner) findViewById(R.id.spnStaff);
        String[] staff = new String[]{"Staff 1", "Staff 2", "Staff 3", "Staff 4", "Staff 5"};
        ArrayAdapter<String> adapterStaff = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, staff);
        spnStaff.setAdapter(adapterStaff);
        spnService = (Spinner) findViewById(R.id.spnService);
        String[] services = new String[]{"Cut and Finish", "Colour", "Men's Hairdressing", "Treatments", "Hair Up and Bridal Hair", "Extensions", "Beauty", "Semi Permanent Hair Straightening", "Wash & Dry"};
        ArrayAdapter<String> adapterServices = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, services);
        spnService.setAdapter(adapterServices);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appointments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //******************* method submit appointment *****************
    public void btnSubmitAppointmentClicked(View view) {
        try {
            //Declare an object of the DB class
            DBBookings appointment = new DBBookings(userName, spnStaff.getSelectedItem().toString(), spnService.getSelectedItem().toString(), txtDateAppoint.getText().toString(), spnTime.getSelectedItem().toString());

            //Add the user
            dbBookingsHandler.addAppointment(appointment);

            //display confirmation message
            Toast.makeText(AppointmentsActivity.this, "Appointment Added", Toast.LENGTH_LONG).show();

            //send the user to the main screen
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        } catch (Exception e) {
            Toast.makeText(AppointmentsActivity.this, "Could not add appointment", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            e.getMessage();
        }

    }//ends submit appointment


    public void btnCancelAppointmentsClicked(View view) {

        //take the user to booking activity
        Intent bookingsIntent = new Intent(AppointmentsActivity.this, BookingsActivity.class);
        startActivity(bookingsIntent);
/*
        //ge the layout login.xml to use and display
        LayoutInflater li = LayoutInflater.from(context);
        View logInView = li.inflate(R.layout.time_picker, null);

        //create the Alert Dialog box
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setView(logInView);

        final DatePicker datePicker = (DatePicker) logInView.findViewById(R.id.datePickerTwo);


        alertDialog
                .setCancelable(true)
                .setPositiveButton("Log In", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String date = String.valueOf(datePicker.getDayOfMonth()) + "/" + String.valueOf(datePicker.getMonth() + 1) + "/" + String.valueOf(datePicker.getYear());

                        //lblHello.setText(date);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TO-DO
                    }
                });


        AlertDialog alertDialogShow = alertDialog.create();
        alertDialogShow.show();
        */

    }

    //****************************** change date button clicked
    public void btnChangeDateClicked(View view){

        //ge the layout login.xml to use and display
        LayoutInflater li = LayoutInflater.from(context);
        View logInView = li.inflate(R.layout.time_picker, null);

        //create the Alert Dialog box to call the date picker
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setView(logInView);

        final DatePicker datePicker = (DatePicker) logInView.findViewById(R.id.datePickerTwo);

        alertDialog
                .setCancelable(true)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String date = String.valueOf(datePicker.getDayOfMonth()) + "/" + String.valueOf(datePicker.getMonth() + 1) + "/" + String.valueOf(datePicker.getYear());
                        txtDateAppoint.setText(date);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TO-DO
                    }
                });

        AlertDialog alertDialogShow = alertDialog.create();
        alertDialogShow.show();
    }

}//ends class
























