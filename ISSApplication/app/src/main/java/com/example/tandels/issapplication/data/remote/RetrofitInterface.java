package com.example.tandels.issapplication.data.remote;

import com.example.tandels.issapplication.data.model.ResponseList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * RetrofitInterface class
 */
public interface RetrofitInterface {
    @GET("iss-pass.json?")
    Call<ResponseList> getResponseList(@Query("lat") double lat, @Query("lon") double lon);

}
