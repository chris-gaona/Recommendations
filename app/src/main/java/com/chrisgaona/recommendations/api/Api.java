package com.chrisgaona.recommendations.api;

import com.chrisgaona.recommendations.model.ActiveListings;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by chrisgaona on 12/4/17.
 */

public interface Api {

    @GET("/listing/active")
    void activeListings(@Query("includes") String includes,
                        Callback<ActiveListings> callback);
}
