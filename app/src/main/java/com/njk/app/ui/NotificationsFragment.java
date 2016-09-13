package com.njk.app.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.njk.app.firebase.Firebase;
import com.njk.app.ui.adapters.HomeAdapter;
import com.njk.app.ui.adapters.NotificationAdapter;


public class NotificationsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NotificationAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.tit_notifications));


        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mAdapter = new NotificationAdapter(getActivity());


        mRecyclerView.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return view;
    }


}
