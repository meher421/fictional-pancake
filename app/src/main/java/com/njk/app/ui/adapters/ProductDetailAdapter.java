package com.njk.app.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapplication.R;
import com.njk.app.dto.Market;
import com.njk.app.dto.MarketHelper;
import com.njk.app.utils.AppConstants;
import com.njk.app.utils.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by meher on 6/9/16.
 */

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.Holder> {

    private static final String TAG = "ProuctDetailAdapter-123456";
    private HashMap<String, Market> marketHashMap;
    private Context mContext;
    private MarketHelper marketHelper = MarketHelper.getInstance();
    private ArrayList<String> mMarkets;
    private String mProductId;

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

    public void refreshAdapterData(){
        Logger.i(TAG, "refreshAdapterData ");
        marketHashMap = marketHelper.getProductsData().get(mProductId);
        notifyDataSetChanged();
    }


    private void configureViewHolder(Holder holder, int position) {
        if (position == 0) {
            holder.mTitleLayout.setVisibility(View.VISIBLE);
        } else {
            holder.mTitleLayout.setVisibility(View.GONE);
        }
        Market market = marketHashMap.get(mMarkets.get(position));

        holder.marketName.setText(mMarkets.get(position));
        holder.dateTxt.setText(market.getDate());
        setStatus(market, holder);

        holder.bagsTxt.setText("" + market.getBags());

    }

    private void setStatus(Market market, Holder holder) {
        int state = market.getState();
        if (state < AppConstants.MarketConstants.MARKET_STEADY) {
            holder.usdTxt.setVisibility(View.VISIBLE);
            holder.status.setVisibility(View.VISIBLE);
            holder.state.setVisibility(View.VISIBLE);
            holder.marketStatus.setVisibility(View.GONE);

            holder.status.setText("\u20B9 " + market.getStatus());
            if (marketHelper.getUsdValue() == null) {
                holder.usdTxt.setVisibility(View.GONE);
            } else {
//                holder.usdTxt.setText("$ " + (double)Math.round(market.getStatus() / marketHelper.getUsdValue()));
                holder.usdTxt.setText("$ " + new BigDecimal(market.getStatus() / marketHelper.getUsdValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if (state == AppConstants.MarketConstants.MARKET_UP)
                holder.state.setImageResource(R.drawable.ic_arrow_upward);
            else if (state == AppConstants.MarketConstants.MARKET_DOWN)
                holder.state.setImageResource(R.drawable.ic_arrow_downward);


        } else {
            holder.usdTxt.setVisibility(View.GONE);
            holder.status.setVisibility(View.GONE);
            holder.state.setVisibility(View.GONE);
            holder.marketStatus.setVisibility(View.VISIBLE);
            if (state == AppConstants.MarketConstants.MARKET_STEADY)
                holder.marketStatus.setText("STEADY");
            else if (state == AppConstants.MarketConstants.MARKET_CLOSED)
                holder.marketStatus.setText("CLOSED");
        }

    }

    @Override
    public int getItemCount() {
        return mMarkets.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView marketName, dateTxt, status, marketStatus, usdTxt, bagsTxt;
        ImageView state;
        View mTitleLayout;

        public Holder(View itemView) {
            super(itemView);
            mTitleLayout = itemView.findViewById(R.id.title_layout);
            marketName = (TextView) itemView.findViewById(R.id.market_place);
            dateTxt = (TextView) itemView.findViewById(R.id.date);
            status = (TextView) itemView.findViewById(R.id.status);
            marketStatus = (TextView) itemView.findViewById(R.id.market_status);
            usdTxt = (TextView) itemView.findViewById(R.id.usd);
            bagsTxt = (TextView) itemView.findViewById(R.id.bags_txt);
            state = (ImageView) itemView.findViewById(R.id.state);
        }
    }
}
