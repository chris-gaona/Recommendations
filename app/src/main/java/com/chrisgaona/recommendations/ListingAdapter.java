package com.chrisgaona.recommendations;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chrisgaona.recommendations.model.ActiveListings;
import com.chrisgaona.recommendations.model.Listing;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by chrisgaona on 12/4/17.
 */

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ListingHolder> implements Callback<ActiveListings> {

    private MainActivity mMainActivity;
    private final LayoutInflater inflater;
    private ActiveListings mActiveListings;

    public ListingAdapter (MainActivity activity) {
        mMainActivity = activity;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public ListingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListingHolder(inflater.inflate(R.layout.layout_listing, parent, false));
    }

    @Override
    public void onBindViewHolder(ListingHolder holder, int position) {
        final Listing listing = mActiveListings.results[position];
        holder.mTitleView.setText(listing.title);
        holder.mPriceView.setText(listing.price);
        holder.mShopNameView.setText(listing.Shop.shop_name);

        Picasso.with(holder.mImageView.getContext())
                .load(listing.Images[0].url_570xN)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        if (mActiveListings == null) {
            return 0;
        }

        if (mActiveListings.results == null) {
            return 0;
        }

        return mActiveListings.results.length;
    }

    @Override
    public void success(ActiveListings activeListings, Response response) {
        mActiveListings = activeListings;
        notifyDataSetChanged();
        mMainActivity.showList();
    }

    @Override
    public void failure(RetrofitError error) {
        mMainActivity.showError();
    }

    public ActiveListings getActiveListings() {
        return mActiveListings;
    }

    public class ListingHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTitleView;
        public TextView mShopNameView;
        public TextView mPriceView;

        public ListingHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.listing_image);
            mTitleView = itemView.findViewById(R.id.listing_title);
            mShopNameView = itemView.findViewById(R.id.listing_shop_name);
            mPriceView = itemView.findViewById(R.id.listing_price);
        }
    }
}
