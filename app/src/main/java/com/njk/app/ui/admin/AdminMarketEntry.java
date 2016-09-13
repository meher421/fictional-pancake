package com.njk.app.ui.admin;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.myapplication.R;
import com.njk.app.dto.Market;
import com.njk.app.firebase.Firebase;
import com.njk.app.utils.Logger;
import com.njk.app.utils.Util;

import java.util.HashMap;
import java.util.Map;

public class AdminMarketEntry extends AppCompatActivity {

    private static String TAG = "AdminMarket-123456";
    TextInputLayout mMarketText, mProductText, mBagsText, mMarketStatus, mStatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_market_entry);

        mMarketText = (TextInputLayout) findViewById(R.id.market_name);
        mProductText = (TextInputLayout) findViewById(R.id.product_name);
        mBagsText = (TextInputLayout) findViewById(R.id.bags);

        mMarketStatus = (TextInputLayout) findViewById(R.id.market_status);
        mStatusText = (TextInputLayout) findViewById(R.id.status);


    }


    public void onSubmit(View view) {

        String productName = mProductText.getEditText().getText().toString();
        String marketName = mMarketText.getEditText().getText().toString();
        int marketStatus = Integer.parseInt(mMarketStatus.getEditText().getText().toString());
        Double status = Double.parseDouble(mStatusText.getEditText().getText().toString());
        int bags = Integer.parseInt(mBagsText.getEditText().getText().toString());

        String todaysDate = Util.getTodayDate();
        long millisec = System.currentTimeMillis();


        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("title", productName);
        stringMap.put("body", marketName);

        DatabaseReference messageRef = Firebase.getInstance().getReference("GlobalMarket").child("data").child("messages");
        messageRef.child("" + millisec).setValue(stringMap);
        Market market = new Market(marketName, status, marketStatus, bags, todaysDate, millisec);


        Firebase.getInstance().getReference("GlobalMarket").child("data").child("products").child(productName).child(marketName).setValue(market);

//        HashMap<String, Market> marketHashMap = new HashMap<>(3);
//        marketHashMap.put(marketName, market);

       /* HashMap<String, Market> productData = MarketHelper.getInstance().getProduct(productName);

        if (productData != null) { //product found
            Market marketData = productData.get(marketName);
            if (marketData != null) { //product found
                marketData.setName(marketName);
                marketData.setStatus(status);
                marketData.setState(marketStatus);
                marketData.setDate(todaysDate);
                marketData.setBags(bags);
            } else { //market not found
                marketData = new Market(marketName, status, marketStatus, bags, todaysDate, millisec);
                productData.put(marketName, marketData);
            }

        } else {
            //product not found
            productData = new HashMap<>();
            Market marketData = new Market(marketName, status, marketStatus, bags, todaysDate, millisec);
            productData.put(marketName, marketData);

        }

        Map<String, HashMap<String, Market>> productHashMap = MarketHelper.getInstance().getProductsData();

        productHashMap.put(productName, productData);


        FirebaseDatabase.getInstance().getReference("GlobalMarket").child("data").child("products").setValue(productHashMap);*/
        Logger.i(TAG, "data submitted");

    }
}

