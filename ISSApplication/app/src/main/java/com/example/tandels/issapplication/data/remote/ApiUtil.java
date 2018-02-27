package com.example.tandels.issapplication.data.remote;

/**
 * ApiUtil class
 */
public class ApiUtil {

    public static final String BASE_URL = "http://api.open-notify.org";

    /**
     * create RetrofitService Client
     *
     * @return
     */
    public static RetrofitInterface getService() {
        return RetrofitClient.getClient(BASE_URL).create(RetrofitInterface.class);
    }
}
