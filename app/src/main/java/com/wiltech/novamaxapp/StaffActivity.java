package com.wiltech.novamaxapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class StaffActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        //Implement the List View
        String[] staffs = {"Staff 1", "Staff 2", "Staff 3"};
        final String[] staffDetails = {"Male", "Female", "Male"};
        Integer[] staffImageID = {R.drawable.cut_finish,
                R.drawable.colour,
                R.drawable.mans_hair,
                R.drawable.treatment,
        };

        //create the adpter to convert Array of strings into list items
        ListAdapter wilsAdapter = new ServicesAdapter(this, staffs, staffImageID);

        //declare your list view
        ListView wilsListView = (ListView) findViewById(R.id.lViewStaff);
        //Convert the array of strings and add it to the list view
        wilsListView.setAdapter(wilsAdapter);

        //create a listener for each item on the list view to respond to touch
        wilsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String staff = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(StaffActivity.this, staffDetails[position], Toast.LENGTH_LONG).show();
                    }
                }
        );//ends setOnItemClickListener
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_staff, menu);
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
}
