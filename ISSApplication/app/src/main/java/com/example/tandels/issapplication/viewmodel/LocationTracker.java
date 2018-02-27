package com.example.tandels.issapplication.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.tandels.issapplication.R;

import static com.example.tandels.issapplication.viewmodel.AppConstants.LOCATION_TRACKER_CLASS;
import static com.example.tandels.issapplication.viewmodel.AppConstants.REQUEST_LOCATION;

/**
 * LocationTracker class
 */

public class LocationTracker {
    private LocationManager locationManager;
    private double longitude, latitude;
    private Activity activity;

    /**
     * Constructor
     *
     * @param locationManager
     * @param activity
     */
    public LocationTracker(LocationManager locationManager, Activity activity) {
        this.activity = activity;
        this.locationManager = locationManager;
        longitude = 0;
        latitude = 0;
    }

    /**
     * find the current location's longitude and latitude
     */
    public void getLocation() {

        if (ActivityCompat.checkSelfPermission(activity.getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity.getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                longitude = location.getLatitude();
                latitude = location.getLongitude();
            } else {
                if(activity!=null) {
                    Log.i(LOCATION_TRACKER_CLASS, activity.getString(R.string.location_not_found));
                }
            }
        }

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
