package com.example.tandels.issapplication.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tandels.issapplication.R;
import com.example.tandels.issapplication.data.model.Response;
import com.example.tandels.issapplication.data.remote.DownloadResponseService;
import com.example.tandels.issapplication.viewmodel.LocationTracker;
import com.example.tandels.issapplication.viewmodel.ResponseDataAdapter;

import java.util.ArrayList;

import static com.example.tandels.issapplication.util.AppConstants.LATITUDE;
import static com.example.tandels.issapplication.util.AppConstants.LONGITUDE;
import static com.example.tandels.issapplication.util.AppConstants.REQUEST_LOCATION;
import static com.example.tandels.issapplication.util.AppConstants.RESPONSE_RESULT;
import static com.example.tandels.issapplication.util.AppConstants.RESPONSE_SERVICE_ACTION;

/**
 * MainActivity class
 */
public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private ResponseDataAdapter mResponseAdapter;
    private LocationManager locationManager;
    private LocationTracker locationTracker;
    private double longitude;
    private double latitude;
    private ResponseReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);//create location manager instance
        locationTracker = new LocationTracker(locationManager, this);//create location tracker instance
        locationTracker.getLocation(); // get current location of device

        //get longitude & latitude of device location
        longitude = locationTracker.getLongitude();
        latitude = locationTracker.getLatitude();
    }


    /**
     * Start the Intent service
     */
    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(this, DownloadResponseService.class);
        intent.putExtra(LONGITUDE, longitude);
        intent.putExtra(LATITUDE, latitude);
        startService(intent);//start service with input as longitude/latitude
    }

    /**
     * Register the Broadcast Receiver
     */
    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(RESPONSE_SERVICE_ACTION);
        receiver = new ResponseReceiver();
        if (receiver != null) {//register receiver
            LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
        }
    }

    /**
     * Unregister the Broadcast Receiver
     */
    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION:
                locationTracker.getLocation();
                break;
        }
    }

    /**
     * Inner class Response Receiver
     */
    private class ResponseReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Response> responseList = (ArrayList<Response>) intent.getSerializableExtra(RESPONSE_RESULT);
            generateResponseList(responseList);
        }
    }


    /**
     * Method to generate List of responses(duration,risetime) using RecyclerView with custom adapter
     */
    private void generateResponseList(ArrayList<Response> responseList) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mResponseAdapter = new ResponseDataAdapter(responseList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mResponseAdapter);
    }
}
