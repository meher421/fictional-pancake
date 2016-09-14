package com.njk.app.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.R;
import com.njk.app.dto.MarketHelper;
import com.njk.app.dto.Message;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageDetailFragment extends Fragment {


    private int index = -1;
    private ArrayList<Message> marketHelper;

    public MessageDetailFragment() {
        // Required empty public constructor
    }

    public static MessageDetailFragment init(int val) {
        MessageDetailFragment fragment = new MessageDetailFragment();


        Bundle args = new Bundle();
        args.putInt("val", val);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments() != null ? getArguments().getInt("val") : -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_detail, container, false);

        marketHelper = MarketHelper.getInstance().getMessageList();

        TextView title = (TextView) view.findViewById(R.id.title);

        TextView body = (TextView) view.findViewById(R.id.body);

        Message message = marketHelper.get(index);

        title.setText(message.getTitle());
        body.setText(message.getBody());
        // Inflate the layout for this fragment
        return view;
    }


}
