package com.njk.app.admin;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.myapplication.R;
import com.njk.app.utils.Logger;
import com.njk.app.testdto.Product;
import com.njk.app.testdto.uplink.UpLinkImpl;
import com.njk.app.testdto.uplink.UpLinkInterface;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<Product> mProducts;
    private MyAdapter mAdapter;
    private ViewPager mPager;
    private Button mAddProduct;
    private Button mPreview;
    private UpLinkInterface mUpLinkInterface;
    private String TAG = "AdminActivity-123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mPager = (ViewPager) findViewById(R.id.pager);
        mAddProduct = (Button) findViewById(R.id.add_product);
        mPreview = (Button) findViewById(R.id.preview);

        mAddProduct.setOnClickListener(this);

        mUpLinkInterface = new UpLinkImpl();

        mProducts = mUpLinkInterface.getAllProducts();
        mAdapter = new MyAdapter(getSupportFragmentManager());

        if (mProducts.size() == 0) {
            mUpLinkInterface.createProduct(-1, "", null);
            mAdapter.addFragment();
        } else {
            mAdapter.init(mProducts.size());
        }


        mPager.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.add_product) {

            if (TextUtils.isEmpty(mAddProduct.getText().toString())) {

                Toast.makeText(AdminActivity.this, "Market name error", Toast.LENGTH_LONG).show();
                return;
            }


            Product product = mAdapter.getItem(mPager.getCurrentItem()).submitProduct();


            if (product == null) {
                return;
            }
            product.setId(mPager.getCurrentItem());
            mUpLinkInterface.createProduct(-1, "", null);
            mAdapter.addFragment();
            mAdapter.notifyDataSetChanged();
            mPager.setCurrentItem(mProducts.size());


        } else if (id == R.id.preview) {

        }

    }

    public interface OnClickAdapterItem {
        void onClickAdapterItem(View view, int position);

    }

    public class MyAdapter extends FragmentStatePagerAdapter {

        ArrayList<AdminFragment> fragments = new ArrayList<>();

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment() {
            Logger.i(TAG, " products size ;  " + mProducts.size());
            int id = -1;

            id = mProducts.get(fragments.size()).getId();
            fragments.add(AdminFragment.init(id));

        }

        @Override
        public int getCount() {
            int size = mProducts.size();
            return size > 0 ? size : 1;
        }


        @Override
        public AdminFragment getItem(int position) {
            return fragments.get(position);
        }


        public void init(int size) {
            for (int i = 0; i < size; i++) {
                fragments.add(AdminFragment.init(mProducts.get(i).getId()));
            }
        }
    }
}
