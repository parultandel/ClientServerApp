package com.example.tandels.issapplication.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * List of Responses
 */
public class ResponseList {

    @SerializedName("response")
    private ArrayList<Response> responseArrayList = null;

    public ArrayList<Response> getResponseArrayList() {
        return responseArrayList;
    }
}
