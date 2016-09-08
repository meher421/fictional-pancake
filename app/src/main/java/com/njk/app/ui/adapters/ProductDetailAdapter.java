package com.njk.app.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.R;
import com.njk.app.dto.Market;
import com.njk.app.dto.MarketHelper;
import com.njk.app.utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by meher on 6/9/16.
 */

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.Holder> {

    private static final String TAG = "ProuctDetailAdapter-123456";
    private Context mContext;
    private MarketHelper marketHelper = MarketHelper.getInstance();
    private ArrayList<String> mMarkets;
    private String mProductId;
    HashMap<String, Market> marketHashMap;

    public ProductDetailAdapter(Context context, String productId) {
        mContext = context;
        mMarkets = marketHelper.getMarket(productId);
        Logger.i(TAG, "markets size : " + mMarkets.size());
        mProductId = productId;
        marketHashMap = marketHelper.getProductsData().get(productId);
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
        Market market  =marketHashMap.get(mMarkets.get(position));

        holder.marketName.setText(mMarkets.get(position));
        holder.dateTxt.setText(market.getDate());
        holder.status.setText(""+market.getStatus());

        holder.bagsTxt.setText(""+market.getBags());

        //TODO add and calc dollar status


    }

    @Override
    public int getItemCount() {
        return mMarkets.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView marketName, dateTxt, status, marketStatus, usdTxt, bagsTxt;

        public Holder(View itemView) {
            super(itemView);

            marketName = (TextView) itemView.findViewById(R.id.market_place);
            dateTxt = (TextView) itemView.findViewById(R.id.date);
            status = (TextView) itemView.findViewById(R.id.status);
            marketStatus = (TextView) itemView.findViewById(R.id.market_status);
            usdTxt = (TextView) itemView.findViewById(R.id.usd);
            bagsTxt = (TextView) itemView.findViewById(R.id.bags_txt);
        }
    }
}
