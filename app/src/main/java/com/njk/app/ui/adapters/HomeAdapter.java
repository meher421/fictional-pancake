package com.njk.app.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.R;
import com.njk.app.testadmin.AdminActivity;
import com.njk.app.dto.IMarket;
import com.njk.app.dto.Market;
import com.njk.app.dto.MarketHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by meher on 9/8/16.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private static final String TAG = "HomeAdapter-123456";
    private ArrayList<String> mList;
    private AdminActivity.OnClickAdapterItem onClickAdapterItem;
    private IMarket mDownLink;
    private Context mContext;

    public HomeAdapter(AdminActivity.OnClickAdapterItem listener, Context context) {
        onClickAdapterItem = listener;
        mContext = context;
        mList = new ArrayList<>(9);

        mDownLink = MarketHelper.getInstance();

        for (Map.Entry<String, HashMap<String, Market>> map : mDownLink.getProductsData().entrySet()) {
            mList.add(map.getKey());

        }

    }

//    public void addList() {
//        mList.add("Turmaric");
//        mList.add("cashew");
//        mList.add("badam");
//        mList.add("mirchi");
//
//        notifyDataSetChanged();
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v1 = inflater.inflate(R.layout.home_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v1);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        configureViewHolder(holder, position);
    }


    private void configureViewHolder(ViewHolder holder, int position) {
        String title = mList.get(position);

        holder.mTitle.setText("" + title);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickAdapterItem.onClickAdapterItem(v, getAdapterPosition());
                }
            });
        }
    }
}
