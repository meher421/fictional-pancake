package com.njk.app.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.njk.app.utils.Logger;
import com.njk.app.testadmin.AdminActivity;
import com.njk.app.ui.adapters.HomeAdapter;


public class HomeFragment extends Fragment implements AdminActivity.OnClickAdapterItem{

    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private final String TAG = "HomeFragment-123456";
    private MainActivity mActivity;

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
        mActivity.getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(getString(R.string.tit_home));

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mAdapter = new HomeAdapter(this,getActivity());


        mRecyclerView.setAdapter(mAdapter);


        return view;
    }


    @Override
    public void onClickAdapterItem(View view, int position) {
        Logger.i(TAG,"onClickAdapterItem : "+position);

        Intent intent = new Intent(getActivity(),ProductActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);



    }
}
