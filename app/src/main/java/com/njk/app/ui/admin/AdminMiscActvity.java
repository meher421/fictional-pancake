package com.njk.app.ui.admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.myapplication.R;
import com.njk.app.firebase.Firebase;
import com.njk.app.utils.HttpThread;

/**
 * Created by meher on 13/9/16.
 */
public class AdminMiscActvity extends AppCompatActivity {

    private TextInputLayout mProductText, mMarketText, mMarkProdtext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_misc_activity);

        mProductText = (TextInputLayout) findViewById(R.id.product_name);
        mMarketText = (TextInputLayout) findViewById(R.id.market_name);
        mMarkProdtext = (TextInputLayout) findViewById(R.id.mark_product_name);


    }

    public void onProductDeleteClick(View view) {
        String productName = mProductText.getEditText().getText().toString();
        if (TextUtils.isEmpty(productName)) {
            return;
        }
        Firebase.getInstance().getReference("GlobalMarket").child("data").child("products").child(productName).removeValue();


    }

    public void onMarketDeleteClick(View view) {

        String productName = mMarkProdtext.getEditText().getText().toString();
        String marketName = mMarketText.getEditText().getText().toString();
        if (TextUtils.isEmpty(productName) || TextUtils.isEmpty(marketName)) {
            return;
        }

        Firebase.getInstance().getReference("GlobalMarket").child("data").child("products").child(productName).child(marketName).removeValue();

        new HttpThread().start();

    }


}
