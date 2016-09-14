package com.njk.app.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.R;
import com.njk.app.dto.MarketHelper;
import com.njk.app.dto.Message;

import java.util.ArrayList;

/**
 * Created by meher on 11/9/16.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Holder> {

    private ArrayList<Message> messages;
    private Context mContext;
    private onAdapterViewClickListener clickListener;

    public NotificationAdapter(Context context, onAdapterViewClickListener clickListener) {
        mContext = context;
        this.clickListener = clickListener;
        messages = MarketHelper.getInstance().getMessageList();
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
        Message message = messages.get(position);
        holder.mTitle.setText(message.getTitle());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public interface onAdapterViewClickListener {
        void onAdapterItemClick(View view, int position);

        void onAdapterViewClick(View view);

    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView mTitle;

        public Holder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.notification_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onAdapterItemClick(v, getAdapterPosition());
                }
            });
        }
    }
}
