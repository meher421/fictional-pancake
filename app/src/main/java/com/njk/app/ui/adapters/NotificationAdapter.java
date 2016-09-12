package com.njk.app.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.R;

/**
 * Created by meher on 11/9/16.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Holder> {

    private Context mContext;

    public NotificationAdapter(Context context) {
        mContext = context;
    }

    @Override
    public NotificationAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v1 = inflater.inflate(R.layout.notification_list_item, parent, false);
        NotificationAdapter.Holder viewHolder = new NotificationAdapter.Holder(v1);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        configureViewHolder(holder, position);
    }

    private void configureViewHolder(Holder holder, int position) {
        holder.mTitle.setText("Title");
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView mTitle;

        public Holder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.notification_title);
        }
    }
}
