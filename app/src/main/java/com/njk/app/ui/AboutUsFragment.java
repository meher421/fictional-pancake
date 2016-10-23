package com.njk.app.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.R;

public class AboutUsFragment extends Fragment {

    private String TAG = "AboutUs-123456";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle(getString(R.string.tit_about_us));

        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        TextView textView = (TextView) view.findViewById(R.id.about_info_txt);
        textView.setText(Html.fromHtml(getString(R.string.about_us)));

        return view;
    }

}
