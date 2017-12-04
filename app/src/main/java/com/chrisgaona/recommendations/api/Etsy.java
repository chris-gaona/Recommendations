package com.chrisgaona.recommendations.api;


import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by chrisgaona on 11/30/17.
 */

public class Etsy {
    private static final String API_KEY = "";

    private static RequestInterceptor getInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addEncodedQueryParam("api_key", API_KEY);
            }
        };
    }

    private static Api getApi() {
        return new RestAdapter.Builder()
                .setEndpoint("https://openapi.etsy.com/v2")
                .setRequestInterceptor(getInterceptor())
                .build()
                .create(Api.class);
    }
}
