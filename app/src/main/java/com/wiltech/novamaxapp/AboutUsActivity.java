package com.wiltech.novamaxapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.wiltech.novamaxapp.services.RemindUserService;
import com.wiltech.novamaxapp.video.VideoActivity;


public class AboutUsActivity extends ActionBarActivity {

    ImageButton btnFindUs;
    ImageButton btnCallUs;
    ImageButton btnEmailUs;
    ImageButton btnRegister;
    ImageButton btnShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        btnFindUs = (ImageButton)findViewById(R.id.btnFindUs);
        btnCallUs = (ImageButton)findViewById(R.id.btnCallUs);
        btnEmailUs = (ImageButton)findViewById(R.id.btnEmailUs);
        btnRegister = (ImageButton)findViewById(R.id.btnRegister);
        btnShop = (ImageButton)findViewById(R.id.btnShop);

        Intent notifyService = new Intent(this, RemindUserService.class);
        startService(notifyService);

    }

    //***************************** Methods ********************
    public void btnFindUsClicked(View view){
        //Call google maps intent
        Intent dirIntent = new Intent(this, FindUsActivity.class);
        startActivity(dirIntent);
    }
    public void btnCallUsClicked(View view){
        //open dialer and pass their number
        String novMaxNumber = "+447540595289";
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:" +novMaxNumber));
        startActivity(i);

    }
    public void btnEmailUsClicked(View view){
        Intent dirIntent = new Intent(this, ProductListActivity.class);
        startActivity(dirIntent);
    }
    public void btnRegisterClicked(View view){
        //get the user input
        String userName = "User Name";
        String password = "Password";

        //create the intent
        Intent registerIntent = new Intent(getBaseContext(), RegisterActivity.class);
        //add the variables to Register
        registerIntent.putExtra("userName", userName);
        registerIntent.putExtra("password", password);

        //call the intent
        startActivity(registerIntent);
    }
    public void btnShopClicked(View view){

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.youtube.com/watch?v=AZ_crUAUCxY"));
        AboutUsActivity.this.startActivity(i);

        //Intent videoIntent = new Intent(this, VideoActivity.class);
        //startActivity(videoIntent);
    }


}
