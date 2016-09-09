package com.njk.app.testadmin;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.myapplication.R;
import com.njk.app.utils.Logger;
import com.njk.app.testdto.Market;

import java.util.ArrayList;

/**
 * Created by meher on 26/7/16.
 */

public class AdminEditAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int READ_MODE = 1;
    public static final int WRITE_MODE = 2;
    private AdminActivity.OnClickAdapterItem mClickListener;
    private ArrayList<Market> mMarkets;
    private Context mContext;
    private String TAG = "AdminEditAdapter-123456";

    AdminEditAdapter(ArrayList<Market> markets, Context context, AdminActivity.OnClickAdapterItem clik) {
        mMarkets = markets;
        mContext = context;
        mClickListener = clik;
        if (mMarkets.size() == 0) {
            mMarkets.add(new Market(-1, "test", 0, 0,2));
            Logger.i(TAG, " constructor");
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        Logger.i(TAG, "onCreateViewHolder : " + viewType);

        if (viewType == READ_MODE) {

            View v1 = inflater.inflate(R.layout.market_read_layout, parent, false);
            viewHolder = new ReadHolder(v1);
        } else {

            View v2 = inflater.inflate(R.layout.market_edit_layout, parent, false);
            viewHolder = new WriteHolder(v2);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Logger.i(TAG, "onBindViewHolder : " + position);

        if (holder.getItemViewType() == READ_MODE) {
            ReadHolder readHolder = (ReadHolder) holder;
            configureReadHolder(readHolder, position);
        } else {
            WriteHolder writeHolder = (WriteHolder) holder;
            configureWriteHolder(writeHolder, position);
        }

    }

    @Override
    public int getItemViewType(int position) {
        Logger.i(TAG, "getItemViewType : " + position);
        if (mMarkets.get(position).getId() == -1)
            return WRITE_MODE;
        else
            return READ_MODE;
    }

    public Market getItem(int position) {
        return mMarkets.get(position);
    }

    public ArrayList<Market> getList() {
        return mMarkets;
    }

    public void addItem(Market market) {
        mMarkets.add(market);
        int position = mMarkets.size() - 1;
        Logger.i("123456", "additem " + position);
        notifyItemChanged(position - 1);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {

        int size = mMarkets.size();
        Logger.i(TAG, "getItemCount : " + size);
        return mMarkets.size();
    }

    private void configureWriteHolder(WriteHolder writeHolder, int position) {
        Market market = mMarkets.get(position);
        writeHolder.marketTextLay.getEditText().setText("" + market.getName());
        writeHolder.bags.getEditText().setText("" + market.getBags());
        writeHolder.price.getEditText().setText("" + market.getPrice());
        writeHolder.state.getEditText().setText(""+market.getState());
        writeHolder.status.setSelection(market.getState());

    }

    private void configureReadHolder(ReadHolder readHolder, int position) {
        Market market = mMarkets.get(position);
        readHolder.marketTextLay.setText(market.getName());
        readHolder.bags.setText("" + market.getBags());
        readHolder.price.setText("" + market.getPrice());
        readHolder.state.setText(""+market.getState());
        readHolder.status.setText(""+market.getStatus());

        if (position == getItemCount() - 1) {
            readHolder.addMarket.setVisibility(View.VISIBLE);
            readHolder.addMarket.requestFocus();
        } else {
            readHolder.addMarket.setVisibility(View.GONE);
        }

    }

    public class ReadHolder extends RecyclerView.ViewHolder {

        public TextView marketTextLay, bags, price, status, state;
        public Button addMarket;

        public ReadHolder(View itemView) {
            super(itemView);
            marketTextLay = (TextView) itemView.findViewById(R.id.market_name);
            bags = (TextView) itemView.findViewById(R.id.bags);
            price = (TextView) itemView.findViewById(R.id.price);
            addMarket = (Button) itemView.findViewById(R.id.add_market);
            status = (TextView) itemView.findViewById(R.id.status);
            state = (TextView) itemView.findViewById(R.id.state);


            addMarket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onClickAdapterItem(v, getAdapterPosition());
                }
            });
        }
    }

    public class WriteHolder extends RecyclerView.ViewHolder {
        public TextInputLayout marketTextLay, bags, price, state;
        private ImageView action;
        private Spinner status;

        public WriteHolder(View itemView) {
            super(itemView);
            marketTextLay = (TextInputLayout) itemView.findViewById(R.id.market_name);
            bags = (TextInputLayout) itemView.findViewById(R.id.bags);
            price = (TextInputLayout) itemView.findViewById(R.id.price);
            action = (ImageView) itemView.findViewById(R.id.item_action);
            state = (TextInputLayout) itemView.findViewById(R.id.state);
            status = (Spinner) itemView.findViewById(R.id.status);

            action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Market market = getItem(position);
                    market.setId(position);
                    String name = marketTextLay.getEditText().getText().toString();
                    market.setMarketName(name);
                    market.setPrice(Double.parseDouble(price.getEditText().getText().toString()));
                    market.setBags(Long.parseLong(bags.getEditText().getText().toString()));
                    market.setState(Integer.parseInt(state.getEditText().getText().toString()));
                    market.setStatus(status.getSelectedItemPosition());
                    notifyItemChanged(position);
                    mClickListener.onClickAdapterItem(v, getAdapterPosition());
                }
            });
        }
    }
}
