package com.wiltech.novamaxapp;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class EntertainActivity extends ActionBarActivity {
    ImageView imgViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertain);

        imgViewer = (ImageView)findViewById(R.id.imgVHead);

        Drawable[] images = new Drawable[2];
        images[0] = getResources().getDrawable(R.drawable.head);
        images[1] = getResources().getDrawable(R.drawable.hair);

        LayerDrawable layerDrawable = new LayerDrawable(images);
        imgViewer.setImageDrawable(layerDrawable);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entertain, menu);
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
