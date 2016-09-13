package com.njk.app.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;

public class AboutUsFragment extends Fragment {

    private String TAG = "AboutUs-123456";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle(getString(R.string.tit_about_us));

        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

}
