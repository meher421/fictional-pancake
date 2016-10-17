package com.njk.app.ui;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.R;

public class ContactUsFragment extends Fragment {

    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.tit_contact_us));


        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);

        mTextView =(TextView) view.findViewById(R.id.contact_info_txt);
        // Inflate the layout for this fragment

        mTextView.setText(R.string.contact_info_text);
        return view;
    }


}
