package com.njk.app.testadmin;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myapplication.R;
import com.njk.app.testdto.Product;
import com.njk.app.testdto.uplink.UpLinkImpl;
import com.njk.app.testdto.uplink.UpLinkInterface;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminTestFragment extends Fragment {


    public AdminTestFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("AdminTest");
        // Inflate the layout for this fragment
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Test.createTestData2(database);
//        simTest();
        return inflater.inflate(R.layout.fragment_admin_fragmner, container, false);
    }


    public void simTest() {
        Test.createTestData();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dailyMarketRef = database.getReference("DailyMarket");
        dailyMarketRef.removeValue();
        dailyMarket(dailyMarketRef);

        DatabaseReference marketref = database.getReference("Market");
        dailyMarket(marketref);


    }

    void dailyMarket(DatabaseReference databaseReference) {
        UpLinkInterface linkInterface = new UpLinkImpl();


        DatabaseReference dateRef = databaseReference.child(linkInterface.getDate());

        dateRef.child("dollar").setValue(linkInterface.getDollarValue());
        dateRef.child("lastUpdated").setValue(linkInterface.getUpdatedTime());

        DatabaseReference productRef = dateRef.child("products");

        ArrayList<Product> products = linkInterface.getAllProducts();

        for (int i = 0; i < products.size(); i++) {

            productRef.child(products.get(i).getName()).setValue(products.get(i).getMarkets());

        }


        DatabaseReference messageRef = dateRef.child("messages");
        messageRef.setValue(linkInterface.getMessages());
    }

}
