package com.wiltech.novamaxapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class BookingsActivity extends ActionBarActivity {

    //globals
    String userName = "";
    //SimpleDateFormat sdf = new SimpleDateFormat("cc/MM//yy");
    //String date = sdf.format(new Date());
    String date = "";
    TextView lblUserNameBookings;
    CalendarView cViewBookings;

    //DB handler
    DBBookingsHandler dbBookingsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        lblUserNameBookings = (TextView) findViewById(R.id.lblUserNameBookings);

        //Database to read day's select bookings
        dbBookingsHandler = new DBBookingsHandler(this, null, null, 1);
        //String[] todayBookings = dbBookingsHandler.getBookingsFromDB("01/02/2015");


        //******************** check if the user is logged on **************
        if (SaveUserLoggedInSharedPreference.getPrefUserName(BookingsActivity.this).length() == 0) {
            //send to main activity to log in
            Toast.makeText(getApplicationContext(), "Please Log In", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(mainIntent);
        } else {
            //get the user name
            userName = SaveUserLoggedInSharedPreference.getPrefUserName(BookingsActivity.this);

            //Assign the label to the the username
            lblUserNameBookings.setText(userName);
        }

        //Get today's date
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        date = String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);

        //Call hte methgod to poppulate the List view with bookings on htat day
        fillListView();

        //style the CalendarView
        cViewBookings = (CalendarView) findViewById(R.id.cViewBookings);
        cViewBookings.setShowWeekNumber(false);
        cViewBookings.setSelectedDateVerticalBar(R.color.green);
        cViewBookings.setSelectedWeekBackgroundColor(getResources().getColor(R.color.greenOpacity));
        cViewBookings.isClickable();
        cViewBookings.isLongClickable();

        //******************** on date changed calendar ****************
        cViewBookings.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                date = String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);
                Toast.makeText(getApplicationContext(), day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();

                //Call hte methgod to poppulate the List view with bookings on htat day
                fillListView();

            }//ends on select date changed
        });//ends on date change listener


    }//ends on Create


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bookings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //manage action bar icons
        switch (id) {
            case R.id.action_settings: {
                return true;
            }
            case R.id.action_add_appointment: {
                //call the appointment activity
                Intent appointmentIntent = new Intent(this, AppointmentsActivity.class);
                appointmentIntent.putExtra("date", date);
                startActivity(appointmentIntent);

                return true;
            }
            case R.id.action_refresh_bookings: {
                //refresh the bookings
                fillListView();
                Toast.makeText(this, "Bookings updated", Toast.LENGTH_LONG).show();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }//ends switch
    }//ends action bar items


    //*********************** Fill list view **********************************
    public void fillListView() {

        //String to hold the number of values returned from the database
        String todayBookings[];

        //Query the database for the day selected
        todayBookings = dbBookingsHandler.getBookingsFromDB(date);

        //create an array of strings of the size of how many row have returned
        String tBookings[] = new String[todayBookings.length];

        //check for null values
        for(int i =0; i<todayBookings.length;i++){
            if(todayBookings[i] != null){
                tBookings[i] = todayBookings[i];
            }
        }

        //List<String> dbString = new ArrayList<String>();

        //populate the List View
        ListAdapter wilsAdapter = new BookingsListViewAdapter(this, tBookings);

        //declare the list View
        ListView lViewBookings = (ListView) findViewById(R.id.lViewBookings);

        //Convert the array of strings and add it to the list view
        lViewBookings.setAdapter(wilsAdapter);

        //create a listener for each item on the list view to respond to touch
        lViewBookings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String booking = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(BookingsActivity.this, booking, Toast.LENGTH_SHORT).show();
            }
        });//ends on click on list view
    }


}
