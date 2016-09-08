package com.njk.app.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.njk.app.utils.Logger;
import com.njk.app.dto.MarketHelper;

import java.util.ArrayList;

/**
 * Created by meher on 6/9/16.
 */

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.Holder> {

    private static final String TAG = "ProuctDetailAdapter-123456";
    private Context mContext;
    private MarketHelper marketHelper = MarketHelper.getInstance();
    private ArrayList<String> mMarkets;

    public ProductDetailAdapter(Context context, String productId) {
        mContext = context;
        mMarkets = marketHelper.getMarket(productId);
        Logger.i(TAG, "markets size : " + mMarkets.size());
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v1 = inflater.inflate(R.layout.product_child_item_layout, parent, false);
        Holder viewHolder = new Holder(v1);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        configureViewHolder(holder, position);
    }

    private void configureViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
