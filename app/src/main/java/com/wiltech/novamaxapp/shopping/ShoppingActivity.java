package com.wiltech.novamaxapp.shopping;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.wiltech.novamaxapp.R;

public class ShoppingActivity extends ActionBarActivity {

    ImageButton btnPay;
    CheckBox cBoxPOne;
    CheckBox cBoxPTwo;
    CheckBox cBoxPThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        //declare the interface
        btnPay = (ImageButton)findViewById(R.id.btnPay);
        cBoxPOne = (CheckBox)findViewById(R.id.cBoxPOne);
        cBoxPTwo = (CheckBox)findViewById(R.id.cBoxPTwo);
        cBoxPThree = (CheckBox)findViewById(R.id.cBoxPThree);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping, menu);
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

    //*********************** BTN Pay Clicked *******************
    public void btnPayClicked(View view){

        String pOne = "No";
        String pTwo = "No";
        String pThree = "No";

        //get the values from check box
        if(cBoxPOne.isChecked()){
            //save product one into the user's cart
            pOne = "Selected";
        }
        if(cBoxPTwo.isChecked()){
            pTwo = "Selected";
        }
        if(cBoxPThree.isChecked()){
            pThree = "Selected";
        }

        //Call Pay activity and pass the values to add to the cart
        Intent payIntent = new Intent(this, PayActivity.class);
        payIntent.putExtra("pOne", pOne);
        payIntent.putExtra("pTwo", pTwo);
        payIntent.putExtra("pThree", pThree);
        startActivity(payIntent);
    }
}
