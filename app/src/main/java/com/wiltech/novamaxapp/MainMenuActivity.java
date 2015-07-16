package com.wiltech.novamaxapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wiltech.novamaxapp.shopping.ShoppingActivity;


public class MainMenuActivity extends ActionBarActivity {

    //Globals
    TextView lblUserName;
    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //declare the username label
        lblUserName = (TextView) findViewById(R.id.lblUserNameMain);

        //******************** check if the user is logged on **************
        if (SaveUserLoggedInSharedPreference.getPrefUserName(MainMenuActivity.this).length() == 0) {
            //send to main activity to log in
            Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(mainIntent);
        } else {
            //get the user name
            userName = SaveUserLoggedInSharedPreference.getPrefUserName(MainMenuActivity.this);

            //Assign the label to the the username
            lblUserName.setText(userName);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings: {
                return true;
            }
            case R.id.action_sign_out: {

                //display a dialog asking if the user wants to log ou
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Log Out")
                        .setMessage("Are you sure you want to Log Out?")
                        .setPositiveButton("Log Out", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //Log the user by deleting its username from the shared preferences file
                                SaveUserLoggedInSharedPreference.logOut(MainMenuActivity.this);

                                Toast.makeText(getApplicationContext(), "You are now logged off", Toast.LENGTH_LONG).show();

                                //send him back to log in screen
                                Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(mainIntent);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();

                return true;
            }
            case R.id.action_close_down: {
                //closes down the application
                Intent destroyIntent = new Intent(this, ShutDownActivity.class);
                startActivity(destroyIntent);
                return true;
            }
            case R.id.action_directions:{
                //Call Intent directions
                Intent dirIntent = new Intent(this, FindUsActivity.class);
                startActivity(dirIntent);
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //******************** check availability ****************
    public void btnSearchBookingsClicked(View view) {
        Intent bookingsIntent = new Intent(getBaseContext(), BookingsActivity.class);
        //add the variables to Register
        bookingsIntent.putExtra("userName", userName);
        startActivity(bookingsIntent);
    }
   // public void btnCheckCalendarClicked(View view) {

    //}//ends check availability

    //********************* see Staff ********************
    public void btnStaffClicked(View view){
        Intent staffIntent = new Intent(this, StaffActivity.class);
        startActivity(staffIntent);
    }

    //*********************** see Services ****************
    public void btnServicesClicked(View view){
        Intent intentServices = new Intent(this, ServicesActivity.class);
        startActivity(intentServices);
    }

    //*********************** see Products Drawer ****************
    public void btnProductsClicked(View view){
        Intent productsIntent = new Intent(this, ProductsDrawActivity.class);
        startActivity(productsIntent);
    }


    public void btnAboutNovaMaxClicked(View view){
        //Intent aboutIntent = new Intent(this, ProductListActivity.class);
        Intent aboutIntent = new Intent(this, AboutUsActivity.class);
        startActivity(aboutIntent);
    }

    public void btnEntertainClicked(View view){
        Intent aboutIntent = new Intent(this, EntertainActivity.class);
        startActivity(aboutIntent);
    }

    public void btnShoppingClicked(View view){
        Intent shoppingIntent = new Intent(this, ShoppingActivity.class);
        // Intent aboutIntent = new Intent(this, AboutUsActivity.class);
        startActivity(shoppingIntent);
    }

    public void btnMyAppointmentsClicked(View view){
        //Intent aboutIntent = new Intent(this, ProductListActivity.class);
         Intent myBookings = new Intent(this, MyBookingsActivity.class);
        startActivity(myBookings);
    }

}




























