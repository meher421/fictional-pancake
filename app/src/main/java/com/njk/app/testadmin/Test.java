package com.njk.app.testadmin;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.njk.app.testdto.Market;
import com.njk.app.testdto.Messages;
import com.njk.app.testdto.Product;
import com.njk.app.testdto.uplink.UpLinkImpl;
import com.njk.app.testdto.uplink.UpLinkInterface;

import java.util.ArrayList;

/**
 * Created by meher on 6/8/16.
 */

public class Test {

    public static void
    createTestData() {
        UpLinkInterface upLinkInterface = new UpLinkImpl();

        ArrayList<Market> markets1 = new ArrayList<>();
        markets1.add(new Market(1, "Nizmabad", 1000, 89, 1));
        markets1.add(new Market(2, "Bodhan", 600, 23, 2));
        markets1.add(new Market(3, "Armoor", 300, 67, 3));
        markets1.add(new Market(4, "Hyderabad", 500, 45, 4));
        Product product1 = new Product(1, "Turmaric", markets1);

        upLinkInterface.addProduct(product1);


        ArrayList<Market> markets2 = new ArrayList<>();
        markets2.add(new Market(1, "Nizmabad", 1000, 89, 1));
        markets2.add(new Market(2, "Bodhan", 600, 23, 2));
        markets2.add(new Market(3, "Armoor", 300, 67, 3));
        markets2.add(new Market(4, "Hyderabad", 500, 45, 4));
        Product product2 = new Product(2, "Cashew", markets2);

        upLinkInterface.addProduct(product2);

        ArrayList<Market> markets3 = new ArrayList<>();
        markets3.add(new Market(1, "Nizmabad", 1000, 89, 1));
        markets3.add(new Market(2, "Bodhan", 600, 23, 2));
        markets3.add(new Market(3, "Armoor", 300, 67, 3));
        markets3.add(new Market(4, "Hyderabad", 500, 45, 4));
        Product product3 = new Product(3, "Badam", markets3);

        upLinkInterface.addProduct(product3);

        ArrayList<Market> markets4 = new ArrayList<>();
        markets4.add(new Market(1, "Nizmabad", 1000, 89, 1));
        markets4.add(new Market(2, "Bodhan", 600, 23, 2));
        markets4.add(new Market(3, "Armoor", 300, 67, 3));
        markets4.add(new Market(4, "Hyderabad", 500, 45, 4));
        Product product4 = new Product(4, "Sesseme", markets4);

        upLinkInterface.addProduct(product4);

        Messages messages1 = new Messages();
        messages1.setMessage("sample message 1");
        messages1.setLastUpdate(System.currentTimeMillis());
        messages1.setTitle("title 1");

        upLinkInterface.addMessage(messages1);

        Messages messages2 = new Messages();
        messages2.setMessage("sample message 2");
        messages2.setLastUpdate(System.currentTimeMillis());
        messages2.setTitle("title 2");

        upLinkInterface.addMessage(messages2);

        Messages messages3 = new Messages();
        messages3.setMessage("sample message 3");
        messages3.setLastUpdate(System.currentTimeMillis());
        messages3.setTitle("title 3");

        upLinkInterface.addMessage(messages3);

        upLinkInterface.setDollarValue(67.45);


    }

    public static void createTestData2(FirebaseDatabase database) {
        DatabaseReference globalMarket = database.getReference("GlobalMarket");
        globalMarket.removeValue();

        DatabaseReference prodcutRef = globalMarket.child("productsList");
        prodcutRef.child("turmaric").setValue(1);
        prodcutRef.child("cashew").setValue(1);
        prodcutRef.child("badam").setValue(1);
        prodcutRef.child("dhaniya").setValue(1);
        prodcutRef.child("buts").setValue(0);

        DatabaseReference dataRef = globalMarket.child("data");
        DatabaseReference pRef = dataRef.child("products");

        DatabaseReference prodRef = pRef.child("turmaric");

        DatabaseReference nizam = prodRef.child("Nizamabad");

        nizam.child("bags").setValue(122);
        nizam.child("status").setValue(34);
        nizam.child("state").setValue(2);
        nizam.child("date").setValue("28-08-2016");
        nizam.child("timestamp").setValue(123224234);

        DatabaseReference bodhanRef = prodRef.child("Bodhan");

        bodhanRef.child("bags").setValue(122);
        bodhanRef.child("status").setValue(34);
        bodhanRef.child("state").setValue(2);
        bodhanRef.child("date").setValue("28-08-2016");
        bodhanRef.child("timestamp").setValue(234353234);


        
        DatabaseReference BadamprodRef = pRef.child("Badam");

        DatabaseReference nizamBadam = BadamprodRef.child("Nizamabad");

        nizamBadam.child("bags").setValue(122);
        nizamBadam.child("status").setValue(34);
        nizamBadam.child("state").setValue(2);
        nizamBadam.child("date").setValue("28-08-2016");
        nizamBadam.child("timestamp").setValue(234353234);

        DatabaseReference bodhanBadamRef = BadamprodRef.child("Bodhan");

        bodhanBadamRef.child("bags").setValue(122);
        bodhanBadamRef.child("status").setValue(34);
        bodhanBadamRef.child("state").setValue(2);
        bodhanBadamRef.child("date").setValue("28-08-2016");
        bodhanBadamRef.child("timestamp").setValue(234353234);

        DatabaseReference cashew = pRef.child("cashew");

        DatabaseReference nizamcashew = cashew.child("Nizamabad");

        nizamcashew.child("bags").setValue(122);
        nizamcashew.child("status").setValue(34);
        nizamcashew.child("state").setValue(2);
        nizamcashew.child("date").setValue("28-08-2016");
        nizamcashew.child("timestamp").setValue(234353234);

        DatabaseReference bodhancashewRef = cashew.child("Bodhan");

        bodhancashewRef.child("bags").setValue(122);
        bodhancashewRef.child("status").setValue(34);
        bodhancashewRef.child("state").setValue(2);
        bodhancashewRef.child("date").setValue("28-08-2016");
        bodhancashewRef.child("timestamp").setValue(234353234);
        

    }
}
