package com.njk.app.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.njk.app.firebase.Firebase;
import com.njk.app.ui.adapters.NotificationAdapter;
import com.njk.app.utils.Logger;


public class NotificationsFragment extends Fragment implements NotificationAdapter.onAdapterViewClickListener {

    private RecyclerView mRecyclerView;
    private NotificationAdapter mAdapter;
    private MainActivity mActivity;
    private String TAG = "123456-NotificationsFragment";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.tit_notifications));


        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mAdapter = new NotificationAdapter(getActivity(), this);


        mRecyclerView.setAdapter(mAdapter);
//        Firebase.getInstance().goOnline();

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onAdapterItemClick(View view, int position) {

        MessageDetailFragment fragment = MessageDetailFragment.init(position);

        getFragmentManager().beginTransaction().replace(R.id.frame, fragment, "message").addToBackStack("message").commitAllowingStateLoss();

    }

    @Override
    public void onAdapterViewClick(View view) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mActivity != null)
            mActivity.unregisterReceiver(mBroadcastReceiver);
    }

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Logger.i(TAG, "received braodcast");
            mAdapter.notifyDataSetChanged();
        }
    };
}
