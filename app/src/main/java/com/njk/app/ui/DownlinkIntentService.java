package com.njk.app.ui;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.njk.app.dto.IMarket;
import com.njk.app.dto.Market;
import com.njk.app.dto.MarketHelper;
import com.njk.app.dto.Message;
import com.njk.app.firebase.Firebase;
import com.njk.app.firebase.ProductModelFirebaseHelper;
import com.njk.app.testdto.downlink.DayData;
import com.njk.app.testdto.downlink.DownlinkImpl;
import com.njk.app.testdto.downlink.IDownLink;
import com.njk.app.utils.Logger;
import com.njk.app.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class DownlinkIntentService extends IntentService {

    public static final String ACTION_INIT_COMPLETE = "com.njk.app.ui.action_INIT_COMPLETE";
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.njk.app.ui.action.FOO";
    private static final String ACTION_DATA_INIT = "com.njk.app.ui.action.DATA_INIT";
    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.njk.app.ui.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.njk.app.ui.extra.PARAM2";

    private static final String TAG = "IntentService-123456";

    public DownlinkIntentService() {
        super("DownlinkIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DownlinkIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionDataInit(Context context) {
        Intent intent = new Intent(context, DownlinkIntentService.class);
        intent.setAction(ACTION_DATA_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_DATA_INIT.equals(action)) {
                handleActionDataInit();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {

        Logger.i(TAG, "handleActionFoo ");
        final DatabaseReference database = Firebase.getInstance().getDatabase().getReference();
        database.child("DailyMarket").child(Util.getTodayDate()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();

                while (it.hasNext()) {
                    Logger.i(TAG, " iterator : " + it.next());
                }

                DayData data = dataSnapshot.getValue(DayData.class);
                double dPrice = data.getDollar();

                IDownLink downLink = DownlinkImpl.getInstance();
                downLink.setTodaysData(data);

                Logger.i(TAG, " dollar price " + dPrice + ": " + data.getLastUpdated() + " \n  products :: " + (data.getProducts().get("Badam")) + " \n  messages:" + data.getMessages());

                notifyJobDone();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Logger.i(TAG, "queryFailed");

                notifyJobDone();
            }
        });

    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionDataInit() {
        // TODO: Handle action Baz
        Logger.i(TAG, "handleActionDataInit ");


        final DatabaseReference database = Firebase.getInstance().getDatabase().getReference();

        database.child("Tulip").child("data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Logger.i(TAG, "data : " + dataSnapshot);

                //TODO add and parse usd and cal and save
                ProductModelFirebaseHelper data = dataSnapshot.getValue(ProductModelFirebaseHelper.class);
//                Logger.i(TAG, " product data : " + data.getProductNames().get("Badam").get("Bodhan").getTimeStamp());


                if (data == null) {
                    notifyJobDone();
                    return;
                }
                HashMap<String, HashMap<String, Market>> productData = data.getProducts();
                if (productData == null) {
                    notifyJobDone();
                    return;
                }
                //Complete data.
                IMarket marketHelper = MarketHelper.getInstance();
                marketHelper.setProductsData(productData);
                marketHelper.setUsdValue(data.getUsd());

//                Logger.i(TAG, " product data 2 : " + marketHelper.getProductsData().get("Badam").get("Bodhan").getBags());

                //Product names which will be used as keys.
                Object[] products = productData.keySet().toArray();

                ArrayList list = new ArrayList(Arrays.asList(products));
                marketHelper.setProductsNames(list);

                Logger.i(TAG, "products list toString : usd:" + marketHelper.getUsdValue() + " list :" + list.toString());

                // Market names mapped with product names to query markets
                HashMap<String, ArrayList<String>> marketsMap = new HashMap<>();

                for (Object product : list) {
                    String productName = product.toString();
                    Object[] test = data.getProducts().get(productName).keySet().toArray();
                    ArrayList marketList = new ArrayList(Arrays.asList(test));
                    marketsMap.put(productName, marketList);


                    Logger.i(TAG, "Markets list toString : product:" + productName + " markets :" + marketList.toString());
                }
                marketHelper.setMarketsMap(marketsMap);


                notifyJobDone();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                notifyJobDone();

                Logger.i(TAG, "onCancelled :" + databaseError.getMessage());

            }
        });

        database.child("Maple").child("data").child("messages").keepSynced(true);
//        database.child("Market").child("data").child("messages").orderByKey().startAt("1473777863800")
        database.child("Maple").child("data").child("messages").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot == null) {
                    return;
                }

                Logger.i(TAG, " messages :  " + dataSnapshot.toString());
                ArrayList<Message> messageArrayList = new ArrayList<>();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {

                    Message message = eventSnapshot.getValue(Message.class);
//                    Logger.i(TAG, "filtered message :" + message.getTime());
                    messageArrayList.add(message);

                }

                MarketHelper.getInstance().setMessageList(messageArrayList);
                ArrayList<Message> list = MarketHelper.getInstance().getMessageList();
                Logger.i(TAG, "filtered message :" + list.size());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        Logger.i(TAG,"Completed fetching data");

    }


    private void notifyJobDone() {
        Intent intent = new Intent();
        intent.setAction(ACTION_INIT_COMPLETE);
        sendBroadcast(intent);
        Firebase.getInstance().goOffline();
        Firebase.getInstance().removePresenceListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i(TAG, "onDestroy");

    }
}
