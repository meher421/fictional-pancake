package com.njk.app.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.njk.app.utils.Logger;

/**
 * Created by meher on 24/8/16.
 */


public class SplashFragment extends Fragment {

    private static final String TAG = "SplashFragment-123456";
    private MainActivity mActivity;
    private Handler mHandler;
    private int MSG_TIME_OUT =1;
    private int TIME_OUT = 3000;
    private boolean launch =false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = (MainActivity) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            mActivity = (MainActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity.getSupportActionBar().hide();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash_layout, container, false);

        DownlinkIntentService.startActionDataInit(getActivity());
        mHandler = new Handler(mCallBack);
        mHandler.sendEmptyMessageDelayed(MSG_TIME_OUT, TIME_OUT);

        mActivity.registerReceiver(mBroadcastReceiver,new IntentFilter(DownlinkIntentService.ACTION_INIT_COMPLETE));
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity.unregisterReceiver(mBroadcastReceiver);
    }

    Handler.Callback mCallBack = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Logger.i(TAG,"handler message");
            if(msg.what == MSG_TIME_OUT){
                launchHomeFragment();
            }
            return false;
        }
    };

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Logger.i(TAG,"received braodcast");
            launch =true;
            launchHomeFragment();
        }
    };

    private void launchHomeFragment() {
        if(launch)
           mActivity.startHomeFragment();
        else{
            Logger.i(TAG,"still preparing for launch");
        }
    }

}
