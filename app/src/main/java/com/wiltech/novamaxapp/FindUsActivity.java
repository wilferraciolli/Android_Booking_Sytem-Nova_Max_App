package com.wiltech.novamaxapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FindUsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    UiSettings mapSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_us);
        setUpMapIfNeeded();

        //Style the map
        mapSettings = mMap.getUiSettings();
        mapSettings.setScrollGesturesEnabled(true);
        mapSettings.setIndoorLevelPickerEnabled(true);
        mapSettings.setMapToolbarEnabled(true);
        mMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(50.7200,1.8800))
                .title("Nova MAx UK"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(25.2048,55.2708))
                .title("Nova Max Dubai"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.9000,12.4833))
                .title("Nova Max IT"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.3442,6.2675))
                .title("Nova Max NT"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.5167,13.3833))
                .title("Nova Max GM"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(30.0000,25.0000))
                .title("Nova Max AF"));
    }
}
