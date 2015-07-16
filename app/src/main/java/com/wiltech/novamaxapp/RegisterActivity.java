package com.wiltech.novamaxapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends ActionBarActivity {

    //declare the interface
    TextView lblRegister;
    TextView lblName;
    TextView lblUserName;
    TextView lblEmail;
    TextView lblPassword;
    EditText txtName;
    EditText txtUserName;
    EditText txtEmail;
    EditText txtPassword;


   //TextView lblDBResults;

    //Declare the database handler
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //create the objects
        txtUserName = (EditText)findViewById(R.id.txtUserName);
        txtName = (EditText)findViewById(R.id.txtName);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);

        //declare the Intent to get the variables added to it
        Intent dataIntent = getIntent();
        //get the extras variable put to it
        String username = dataIntent.getExtras().getString("userName");
        String password = dataIntent.getExtras().getString("password");
        txtUserName.setText(username);
        txtPassword.setText(password);

        //create a handler DB object
        dbHandler = new DBHandler(this, null, null, 1);

        //read the db to check if user went in
       // String dbString = dbHandler.getUsersFromDB();
        //lblDBResults.setText(dbString);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    //********************* register method ******************************
    public void btnSubmitRegisterClicked(View view){

        try{

            //Declare an object of the DB class
            UsersDB user = new UsersDB(txtName.getText().toString(), txtUserName.getText().toString(), txtEmail.getText().toString(), txtPassword.getText().toString());

            //Add the user
            dbHandler.addUser(user);

           //read the db to check if user went in
            //String dbString = dbHandler.getUsersFromDB();
           // lblDBResults.setText(dbString);

            //display confirmation message
            Toast.makeText(RegisterActivity.this, "User " + txtUserName.getText().toString() + " created", Toast.LENGTH_LONG).show();

            //send the user to the main screen
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(RegisterActivity.this, e.getStackTrace().toString(), Toast.LENGTH_LONG).show();
        }


    }//eds register

    public void btnCancelRegisterClicked(View view){
        Intent i = new Intent(this, MainMenuActivity.class);
        startActivity(i);
    }
}
