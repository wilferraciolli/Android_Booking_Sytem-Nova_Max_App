package com.wiltech.novamaxapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    //Globals and inteface
    Button btnServices;
    Button btnBooking;
    Button btnStaff;
    Button btnLogIn;
    TextView lblUserLoggedOn;

    Context context = this;
    String userName = "";

    //Declare the database handler
    DBHandler dbHandler;

    //boolean to close the app down
    boolean shutDown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the objects
        btnServices = (Button) findViewById(R.id.btnServices);
        btnBooking = (Button) findViewById(R.id.btnBooking);
        btnStaff = (Button) findViewById(R.id.btnStaff);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        lblUserLoggedOn = (TextView)findViewById(R.id.lblUserLoggedOn);

        //create a handler DB object
        dbHandler = new DBHandler(this, null, null, 1);

        //******************** check if the user is logged on **************
        if(SaveUserLoggedInSharedPreference.getPrefUserName(MainActivity.this).length() == 0){
            //send to main activity to log in
        }
        else{
            //get the user name
            userName = SaveUserLoggedInSharedPreference.getPrefUserName(MainActivity.this);

            //Assign the label to the the username
            Intent mainMenuIntent = new Intent(getBaseContext(), MainMenuActivity.class);
            startActivity(mainMenuIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        else if(id == R.id.action_close_down){
            //closes down the application
            Intent destroyIntent = new Intent(this, ShutDownActivity.class);
            startActivity(destroyIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(Process.myPid());
        super.onDestroy();
    }

    //*********************** method to call services activity ***************
    public void btnServicesClicked(View view) {

        Intent intentServices = new Intent(this, ServicesActivity.class);
        startActivity(intentServices);

    }//ends btnServicesClicked

    //******************* btn Booking method ***********************
    public void btnBookingClicked(View view) {
        Intent bookingsIntent = new Intent(this, BookingsActivity.class);
        startActivity(bookingsIntent);

    }//ends btnBooking

    //******************* btn Staff method ***********************
    public void btnStaffClicked(View view) {
        Intent staffIntent = new Intent(this, StaffActivity.class);
        startActivity(staffIntent);

    }//ends btnStaff

    //******************* btn Log In method ***********************
    public void btnLogInClicked(View view) {

        //ge the layout login.xml to use and display
        LayoutInflater li = LayoutInflater.from(context);
        View logInView = li.inflate(R.layout.login, null);

        //create the Alert Dialog box
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setView(logInView);

        //declare the interface
        final TextView lblLogIn = (TextView) logInView.findViewById(R.id.lblLogIn);
        final EditText txtUserName = (EditText) logInView.findViewById(R.id.lblUserNameBookings);
        final EditText txtPassword = (EditText) logInView.findViewById(R.id.txtPassword);
        //final Button btnLogIn = (Button) logInView.findViewById(R.id.btnLogIn);
        final Button btnResetPassword = (Button) logInView.findViewById(R.id.btnResetPassword);
        final Button btnRegister = (Button) logInView.findViewById(R.id.btnRegister);

        alertDialog
                .setCancelable(true)
                .setPositiveButton("Log In", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //get the user input
                        String userName = txtUserName.getText().toString();
                        String password = txtPassword.getText().toString();

                        //Check if user exists on the database
                        userName = dbHandler.checkUser(userName, password).toString();
                        lblUserLoggedOn.setText(userName);

                        if(userName.equals("Not match found")){

                        }
                        else{
                            //save the username to the shared pref file
                            SaveUserLoggedInSharedPreference.setUserNameLoggedIn(MainActivity.this, userName);

                            //create the intent
                            Intent mainMenuIntent = new Intent(getBaseContext(), MainMenuActivity.class);
                            //add the variables to Intent
                            mainMenuIntent.putExtra("userName", userName);
                            startActivity(mainMenuIntent);
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TO-DO
                    }
                });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the user input
                String userName = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();

                //create the intent
                Intent registerIntent = new Intent(getBaseContext(), RegisterActivity.class);
                //add the variables to Register
                registerIntent.putExtra("userName", userName);
                registerIntent.putExtra("password", password);

                //call the intent
                startActivity(registerIntent);
            }
        });

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtUserName.getText().toString();

                //create the intent
                Intent resetIntent = new Intent(getBaseContext(), ResetPasswordActivity.class);
                //add the variables to Register
                resetIntent.putExtra("email", email);

                //call the intent
                startActivity(resetIntent);
            }
        });

        AlertDialog alertDialogShow = alertDialog.create();
        alertDialogShow.show();


        //create the alert diaolog to log in
       /* AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setTitle("Please Enter your Log In Details");
        alertDialog.setMessage("Username: ");
        EditText txtUserName = new EditText(this);
        alertDialog.setView(txtUserName);

        alertDialog.setButton("Log In", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });

        alertDialog.setButton2("Register", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
                Toast.makeText(MainActivity.this, "Take to Register Activity", Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.setButton3("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });


        alertDialog.setCanceledOnTouchOutside(true);

        alertDialog.setIcon(R.drawable.ic_launcher);
        alertDialog.show();
*/
    }//ends btnStaff


}




















