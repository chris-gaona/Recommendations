package com.chrisgaona.recommendations.google;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by chrisgaona on 12/6/17.
 */

public class GoogleServicesHelper implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Activity mActivity;
    private GoogleServicesListener mListener;
    private GoogleApiClient mApiClient;

    public GoogleServicesHelper(Activity activity, GoogleServicesListener listener) {
        mListener = listener;
        mActivity = activity;
        mApiClient = new GoogleApiClient.Builder(mActivity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public interface GoogleServicesListener {
        public void onConnected();
        public void onDisconnected();
    }
}
