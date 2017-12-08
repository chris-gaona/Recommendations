package com.chrisgaona.recommendations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.chrisgaona.recommendations.api.Etsy;
import com.chrisgaona.recommendations.google.GoogleServicesHelper;
import com.chrisgaona.recommendations.model.ActiveListings;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_ACTIVE_LISTINGS = "StateActiveListings";

    private RecyclerView mRecyclerView;
    private View mProgressBar;
    private TextView mErrorView;

    private GoogleServicesHelper mGoogleServicesHelper;
    private ListingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);
        mProgressBar = findViewById(R.id.progressbar);
        mErrorView = findViewById(R.id.error_view);

        // setup recycler view
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        adapter = new ListingAdapter(this);
        mRecyclerView.setAdapter(adapter);

        showLoading();

        mGoogleServicesHelper = new GoogleServicesHelper(this, adapter);

        if (savedInstanceState == null) {
            if (savedInstanceState.containsKey(STATE_ACTIVE_LISTINGS)) {
                adapter.success((ActiveListings) savedInstanceState.getParcelable(STATE_ACTIVE_LISTINGS), null);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleServicesHelper.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleServicesHelper.disconnect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mGoogleServicesHelper.handleActivityResult(requestCode, resultCode, data);

        // update state of plusOneButton
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ActiveListings activeListings = adapter.getActiveListings();
        if (activeListings != null) {
            outState.putParcelable(STATE_ACTIVE_LISTINGS, activeListings);
        }
    }

    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
    }

    public void showList() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }

    public void showError() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }
}
