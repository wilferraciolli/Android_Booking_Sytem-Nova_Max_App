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


public class ServicesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        //Implement the List View
        String[] services = {"Cut and Finish", "Colour", "Men's Hairdressing", "Treatments", "Hair Up and Bridal Hair", "Extensions", "Beauty", "Semi Permanent Hair Straightening", "Wash & Dry"};
        final String[] servicePrice = {"Cut and Finish - 2 hours - £45",
                "Colour 1 hour - £20",
                "Men's Hairdressing - 1 hour - £15",
                "Treatments - 3hours - from £80",
                "Hair Up and Bridal Hair - 3 hours - £100",
                "Extensions - 2 hours - from £50",
                "Beauty - 1 hour - £20",
                "Semi Permanent Hair Straightening - 4 hours - £150",
                "Wash & Dry - 30 min - £15"};
        Integer[] serviceImageID = {R.drawable.cut_finish,
                R.drawable.colour,
                R.drawable.mans_hair,
                R.drawable.treatment,
                R.drawable.bridal,
                R.drawable.extension,
                R.drawable.beauty,
                R.drawable.straightening,
                R.drawable.wash_dry
        };

        //create the adpter to convert Array of strings into list items
        ListAdapter wilsAdapter = new ServicesAdapter(this, services, serviceImageID);

        //declare your list view
        ListView wilsListView = (ListView) findViewById(R.id.lViewServices);
        //Convert the array of strings and add it to the list view
        wilsListView.setAdapter(wilsAdapter);

        //create a listener for each item on the list view to respond to touch
        wilsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(ServicesActivity.this, servicePrice[position], Toast.LENGTH_LONG).show();
                    }
                }
        );//ends setOnItemClickListener

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_services, menu);
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
