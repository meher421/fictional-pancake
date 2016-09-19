package com.njk.app.ui.admin;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.myapplication.R;
import com.njk.app.dto.Market;
import com.njk.app.firebase.Firebase;
import com.njk.app.utils.Logger;
import com.njk.app.utils.Util;

public class AdminMarketEntry extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static String TAG = "AdminMarket-123456";
    private TextInputLayout mMarketText, mProductText, mBagsText, mStatusText;
    private Spinner mSpinner;
    private int mMarketStatus = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_market_entry);

        mMarketText = (TextInputLayout) findViewById(R.id.market_name);
        mProductText = (TextInputLayout) findViewById(R.id.product_name);
        mBagsText = (TextInputLayout) findViewById(R.id.bags);

        mSpinner = (Spinner) findViewById(R.id.market_status);
        mStatusText = (TextInputLayout) findViewById(R.id.status);

        mSpinner.setOnItemSelectedListener(this);

    }


    public void onSubmit(View view) {

        String productName = mProductText.getEditText().getText().toString();
        String marketName = mMarketText.getEditText().getText().toString();
//        int marketStatus = Integer.parseInt(mSpinner.get);
        Double status = Double.parseDouble(mStatusText.getEditText().getText().toString());
        int bags = Integer.parseInt(mBagsText.getEditText().getText().toString());

        String todaysDate = Util.getTodayDate();
        long millisec = System.currentTimeMillis();


        Market market = new Market(marketName, status, mMarketStatus, bags, todaysDate, millisec);


        Firebase.getInstance().getReference("GlobalMarket").child("data").child("products").child(productName).child(marketName).setValue(market);


        DatabaseReference dateRef = Firebase.getInstance().getReference("Market").child(""+Util.getTodayDateInMills());

        dateRef.child("date").setValue(todaysDate);
//        dateRef.child("lastUpdated").setValue(linkInterface.getUpdatedTime());

        DatabaseReference productRef = dateRef.child("products");

        productRef.child(productName).child(marketName).setValue(market);


        Logger.i(TAG, "data submitted");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mMarketStatus = position;
        Logger.i(TAG, "Spinner selected item :" + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

