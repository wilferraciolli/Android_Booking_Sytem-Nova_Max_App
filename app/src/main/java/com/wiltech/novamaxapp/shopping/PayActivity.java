package com.wiltech.novamaxapp.shopping;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wiltech.novamaxapp.MainMenuActivity;
import com.wiltech.novamaxapp.R;

public class PayActivity extends ActionBarActivity {
    String pOne;
    String pTwo;
    String pThree;
    int total = 0;

    TextView lblProductToBuy;
    TextView lblPrice;
    TextView lblTotal;
    Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        //declare the interface
        lblProductToBuy = (TextView) findViewById(R.id.lblProductTobuy);
        lblPrice = (TextView) findViewById(R.id.lblPrice);
        lblTotal = (TextView) findViewById(R.id.lblTotal);
        btnBuy = (Button) findViewById(R.id.btnBuy);


        //get the date chosen from the intent
        Intent dataIntent = getIntent();
        //get the extras variable put to it
        pOne = dataIntent.getExtras().getString("pOne");
        pTwo = dataIntent.getExtras().getString("pTwo");
        pThree = dataIntent.getExtras().getString("pThree");

        //if the user has selected no products display empty
        if (pOne.equals("No") && pTwo.equals("No") && pThree.equals("No")) {
            lblProductToBuy.setText("Your basket is empty!");
            lblPrice.setText("");
            lblTotal.setText("");
            btnBuy.setEnabled(false);
        }

        if(pOne.equals("Selected")) {
            total = 160;
            lblProductToBuy.setText("Full Kit");
            lblPrice.setText("£160");
            lblTotal.setText("Total £" + String.valueOf(total));
            if (pTwo.equals("Selected")) {
                total += 140;
                lblProductToBuy.append("\nAnti Volume");
                lblPrice.append("\n£140");
                lblTotal.setText("Total £" + String.valueOf(total));
            }
            if(pThree.equals("Selected")){
                total += 40;
                lblProductToBuy.append("\nSample");
                lblPrice.append("\n£40");
                lblTotal.setText("Total £" + String.valueOf(total));
            }
        }
        else if(pTwo.equals("Selected")){
            total = 140;
            lblProductToBuy.setText(("Anti Volume"));
            lblPrice.setText("£140");
            lblTotal.setText("Total £" + String.valueOf(total));
            if(pThree.equals("Selected")){
                total = 180;
                lblProductToBuy.append("\nSample");
                lblPrice.append("\n£40");
                lblTotal.setText("Total £" + String.valueOf(total));
            }
        }
        else if(pThree.equals("Selected")){
            total = 40;
            lblProductToBuy.setText("Sample");
            lblPrice.setText("£40");
            lblTotal.setText("Total £" + String.valueOf(total));
        }

    }//ends on create


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pay, menu);
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

    public void btnBuyClicked(View view){
        Intent i = new Intent(this, MainMenuActivity.class);
        startActivity(i);
    }
}
