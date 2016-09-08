package com.njk.app.ui;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.njk.app.utils.Logger;
import com.myapplication.R;

public class AboutUsFragment extends Fragment {

    private String TAG = "AboutUs-123456";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle(getString(R.string.tit_about_us));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference market = database.getReference("Market");

        market.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> data = dataSnapshot.getChildren();

                while(data.iterator().hasNext()){
                    String child = data.iterator().next().toString();
                    Logger.i(TAG,"data : "+child);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//
//        Query myTopPostsQuery = market.getRoot().rderByKey();
//        Logger.i(TAG,"result :: "+myTopPostsQuery.toString());



        // Inflate the layout for this fragment
        Logger.i(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

}
