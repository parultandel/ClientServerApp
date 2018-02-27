package com.example.tandels.issapplication.data.remote;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.tandels.issapplication.data.model.Response;
import com.example.tandels.issapplication.data.model.ResponseList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

import static com.example.tandels.issapplication.viewmodel.AppConstants.DOWNLOAD_RESPONSE_SERVICE;
import static com.example.tandels.issapplication.viewmodel.AppConstants.LATITUDE;
import static com.example.tandels.issapplication.viewmodel.AppConstants.LONGITUDE;
import static com.example.tandels.issapplication.viewmodel.AppConstants.RESPONSE_RESULT;
import static com.example.tandels.issapplication.viewmodel.AppConstants.RESPONSE_SERVICE_ACTION;

/**
 * Download response data from the server
 */
public class DownloadResponseService extends IntentService {
    /**
     * constructor
     */
    public DownloadResponseService() {
        super(DOWNLOAD_RESPONSE_SERVICE);
    }

    /**
     * Call RetrofitService API and retrieve response
     *
     * @param intent
     */
    @Override
    public void onHandleIntent(@Nullable Intent intent) {
        double longitude = intent.getDoubleExtra(LONGITUDE, 0);
        double latitude = intent.getDoubleExtra(LATITUDE, 0);
        RetrofitInterface mRetrofitService = ApiUtil.getService();
        Call<ResponseList> call = mRetrofitService.getResponseList(longitude, latitude);
        if (call != null && call.request() != null && call.request().url() != null) {
            Log.i("URL", call.request().url().toString());
            //invoke callback
            call.enqueue(new Callback<ResponseList>() {
                @Override
                public void onResponse(Call<ResponseList> call, retrofit2.Response<ResponseList> response) {
                    if (response != null) {
                        ResponseList responseData = response.body();
                        // generateResponseList(responseData.getResponseArrayList());
                        publishResults(responseData.getResponseArrayList());
                        Log.i("Successful", response.body().toString());
                    } else {
                        Log.i("Successful", "With null data");
                    }
                }

                @Override
                public void onFailure(Call<ResponseList> call, Throwable t) {
                    Log.e("onFailure", t.getMessage());
                }
            });
        }


    }

    /**
     * Broadcast result to the UI
     *
     * @param responseList
     */
    private void publishResults(ArrayList<Response> responseList) {
        Intent intent = new Intent();
        intent.setAction(RESPONSE_SERVICE_ACTION);
        intent.putExtra(RESPONSE_RESULT, responseList);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(intent);
    }
}
