package com.wiltech.novamaxapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class ResetPasswordActivity extends ActionBarActivity {

    //create an object for notification - Globals
    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;
    EditText txtEmailRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        txtEmailRegister = (EditText)findViewById(R.id.txtEmailReset);

        //build the notification
        notification = new NotificationCompat.Builder(this);
        //clear the notification once clicked
        notification.setAutoCancel(true);

        //declare the Intent to get the variables added to it
        Intent dataIntent = getIntent();
        //get the extras variable put to it
        String username = dataIntent.getExtras().getString("email");
        txtEmailRegister.setText(username + "@hotmail.com");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reset_password, menu);
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

    //******************** reset password **********************
    public void btnResetPasswordClicked(View view){
        //Send email reset password here

        //build the notification - icon/text/time
        //icon
        notification.setSmallIcon(R.drawable.ic_launcher);
        //ticker
        notification.setTicker("Password reset and sent to your email");
        //time
        notification.setWhen(System.currentTimeMillis());
        //Content
        notification.setContentTitle("Password reset");
        notification.setContentText("Your pasword has been reset and emailed to you.");

        //manage what to do once clicked
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Builds notification and issues it
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());


    }//ends reset password
}
