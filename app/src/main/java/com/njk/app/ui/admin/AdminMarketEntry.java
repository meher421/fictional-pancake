package com.njk.app.ui.admin;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.myapplication.R;
import com.njk.app.dto.Market;
import com.njk.app.dto.MarketHelper;
import com.njk.app.firebase.Firebase;
import com.njk.app.utils.HttpThread;
import com.njk.app.utils.Logger;
import com.njk.app.utils.Util;

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

public class AdminMarketEntry extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static String TAG = "AdminMarket-123456";
    private ArrayList<String> products, mMarkets;
    private ArrayAdapter<String> marketAdapter;
    private TextInputLayout mMarketTextLayout, mProductText, mBagsText, mStatusText;
    private Spinner mSpnProductName, mSpnMarketName, mSpinner;
    private int mMarketStatus = 1;
    private String mProductName, mMarketName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_market_entry);

        mSpnProductName = (Spinner) findViewById(R.id.spn_product_name);
        mSpnMarketName = (Spinner) findViewById(R.id.spn_market_name);
        mMarketTextLayout = (TextInputLayout) findViewById(R.id.market_name);
        mProductText = (TextInputLayout) findViewById(R.id.product_name);
        mBagsText = (TextInputLayout) findViewById(R.id.bags);

        mSpinner = (Spinner) findViewById(R.id.market_status);
        mStatusText = (TextInputLayout) findViewById(R.id.status);

        mSpinner.setOnItemSelectedListener(this);
        mSpnProductName.setOnItemSelectedListener(this);
        mSpnMarketName.setOnItemSelectedListener(this);

        products = MarketHelper.getInstance().getProductNames();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.add("NONE");
        if (products != null)
            adapter.addAll(products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnProductName.setAdapter(adapter);


        marketAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        marketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnMarketName.setAdapter(marketAdapter);
    }


    public void onSubmit(View view) {

        String productName = (mProductText.getVisibility() == View.VISIBLE) ? mProductText.getEditText().getText().toString() : mProductName;
        String marketName = (mMarketTextLayout.getVisibility() == View.VISIBLE) ? mMarketTextLayout.getEditText().getText().toString() : mMarketName;
//        int marketStatus = Integer.parseInt(mSpinner.get);
        String statusText = mStatusText.getEditText().getText().toString();
        String bags = mBagsText.getEditText().getText().toString();
        Logger.i("123456", "status text :" + statusText);


        String todaysDate = Util.getTodayDate();
        long millisec = System.currentTimeMillis();
        Market market = null;

        if (TextUtils.isEmpty(statusText)) {
            market = MarketHelper.getInstance().getProduct(productName).get(marketName);
            if (market == null) {
                Toast.makeText(this, "Market not found", Toast.LENGTH_SHORT).show();
                return;
            }
            market.setState(mMarketStatus);
            market.setDate(todaysDate);
            market.setTimeStamp(millisec);
            if (!TextUtils.isEmpty(bags))
                market.setBags(Integer.parseInt(bags));

        } else {
            Double status = Double.parseDouble(statusText);
            int bagsNo = Integer.parseInt(bags);
            market = new Market(marketName, status, mMarketStatus, bagsNo, todaysDate, millisec);
        }


        Firebase.getInstance().getReference("GlobalMarket").child("data").child("products").child(productName).child(marketName).setValue(market);


        DatabaseReference dateRef = Firebase.getInstance().getReference("Market").child("" + Util.getTodayDateInMills());
        dateRef.child("date").setValue(todaysDate);
        DatabaseReference productRef = dateRef.child("products");
        productRef.child(productName).child(marketName).setValue(market);

        Logger.i(TAG, "data submitted");

        new HttpThread().start();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Logger.i(TAG, "Spinner selected item :" + position);
        if (parent.getId() == R.id.spn_product_name) {

            if (position > 0) {
                mProductText.setVisibility(View.INVISIBLE);
                marketAdapter.clear();
                mMarkets = MarketHelper.getInstance().getMarketsMap().get(products.get(position - 1));

                marketAdapter.add("NONE");
                if (mMarkets != null)
                    marketAdapter.addAll(mMarkets);
                mProductName = products.get(position - 1);

            } else {
                mProductText.setVisibility(View.VISIBLE);
                marketAdapter.clear();
                mProductName = "";
            }

            marketAdapter.notifyDataSetChanged();

        } else if (parent.getId() == R.id.spn_market_name) {
            if (position > 0) {
                mMarketTextLayout.setVisibility(View.INVISIBLE);
                mMarketName = mMarkets.get(position - 1);
            } else {
                mMarketTextLayout.setVisibility(View.VISIBLE);
                mMarketName = "";
            }

        } else if (parent.getId() == R.id.market_status) {
            mMarketStatus = position;
            Logger.i(TAG, "Spinner selected item :" + position);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

