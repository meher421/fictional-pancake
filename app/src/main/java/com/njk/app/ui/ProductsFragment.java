package com.njk.app.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.njk.app.utils.Logger;
import com.njk.app.ui.adapters.ProductDetailAdapter;

/**
 * Created by meher on 21/8/16.
 */

public class ProductsFragment extends Fragment implements View.OnClickListener {

    private static String TAG = "ProductsFragment-123456";
    private String productId = "";
    private ProductDetailAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public static ProductsFragment init(String val) {
        ProductsFragment fragment = new ProductsFragment();


        Bundle args = new Bundle();
        args.putString("val", val);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productId = getArguments() != null ? getArguments().getString("val") : "";

        Logger.i(TAG,"onCreate : "+productId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logger.i(TAG, "oncreateview "+productId);

        getActivity().setTitle(productId);
        View view = inflater.inflate(R.layout.product_parent_layout, container, false);

        mRecyclerView =(RecyclerView)view.findViewById(R.id.recycler_view);

        mAdapter = new ProductDetailAdapter(getActivity(),productId);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.i(TAG, "onResume "+productId);
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public void onClick(View v) {

    }
}
