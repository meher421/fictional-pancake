package com.njk.app.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.myapplication.R;
import com.njk.app.utils.Logger;
import com.njk.app.dto.IMarket;
import com.njk.app.dto.MarketHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by meher on 23/8/16.
 */

public class ProductActivity extends AppCompatActivity {

    private ViewPager mPager;
    private MyAdapter mAdapter;
    private Context mContext;
    private ArrayList<String> mProducts;
    private IMarket market;
    private ActionBar mActionBar;

    private String TAG = "ProductActivity-123456";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity_main);
        mPager = (ViewPager) findViewById(R.id.pager);

        int position = getIntent().getExtras().getInt("position");

        mProducts = MarketHelper.getInstance().getProductNames();

        registerReceiver(mBroadcastReceiver, new IntentFilter(DownlinkIntentService.ACTION_INIT_COMPLETE));

        Logger.i(TAG, "mProducts :" + mProducts.toString());
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(mProducts.get(position));

        market = MarketHelper.getInstance();
        int productsCount = mProducts.size();

        mAdapter = new ProductActivity.MyAdapter(getSupportFragmentManager(), productsCount);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(position);

        //new Thread(new MyThread()).start();

    }

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Logger.i(TAG, "received braodcast,current page: "+mPager.getCurrentItem());
            mAdapter.notifyDataSetChanged();

            mAdapter.fragments.get(mPager.getCurrentItem()).refreshAdapterData();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mBroadcastReceiver);
    }

    public class MyAdapter extends FragmentStatePagerAdapter {

        public ArrayList<ProductsFragment> fragments = new ArrayList<>();

        public MyAdapter(FragmentManager fragmentManager, int size) {
            super(fragmentManager);

            init(size);
        }

        @Override
        public int getCount() {
            int size = mProducts.size();
            return size;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            mActionBar.setTitle(mProducts.get(position));
        }


        @Override
        public ProductsFragment getItem(int position) {
            return fragments.get(position);
        }


        public void init(int size) {
            for (int i = 0; i < size; i++) {
                fragments.add(ProductsFragment.init(mProducts.get(i)));
            }
        }

    }

    class MyThread implements Runnable {

        @Override
        public void run() {
            HashMap<String,String> map = new HashMap<>();
            map.put("message","CMD:2");
            performPostCall("https://njk-web-app.appspot.com/send",map);
        }
    }

    public String performPostCall(String requestURL,
                                  HashMap<String, String> postDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
