package com.njk.app.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.myapplication.R;
import com.njk.app.utils.Logger;
import com.njk.app.dto.IMarket;
import com.njk.app.dto.MarketHelper;

import java.util.ArrayList;

/**
 * Created by meher on 23/8/16.
 */

public class ProductActivity extends AppCompatActivity {

    private ViewPager mPager;
    private MyAdapter mAdapter;
    private Context mContext;
    private ArrayList<String> mProducts;
    private IMarket market;

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

        Logger.i(TAG,"mProducts :"+mProducts.toString());

        market = MarketHelper.getInstance();
        int productsCount = mProducts.size();

        mAdapter = new ProductActivity.MyAdapter(getSupportFragmentManager(), productsCount);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(position);


    }


    public class MyAdapter extends FragmentStatePagerAdapter {

        ArrayList<ProductsFragment> fragments = new ArrayList<>();

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
        public ProductsFragment getItem(int position) {
            return fragments.get(position);
        }


        public void init(int size) {
            for (int i = 0; i < size; i++) {
                fragments.add(ProductsFragment.init(mProducts.get(i)));
            }
        }
    }
}
