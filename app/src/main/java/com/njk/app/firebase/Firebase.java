package com.njk.app.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by meher on 12/9/16.
 */

public class Firebase {

    private static FirebaseDatabase database;
    private static Firebase mInstance;
    private static DatabaseReference mPresenceReference;
    private static PresenceValueEventListener mPresenceListener;

    private Firebase() {
    }

    public static Firebase getInstance() {
        if (mInstance == null) {
            mInstance = new Firebase();

        }
        return mInstance;
    }


    public FirebaseDatabase getDatabase() {
        if (database == null) {
            database = FirebaseDatabase.getInstance();
            mPresenceReference = database.getReference(".info/connected");
            mPresenceListener = new PresenceValueEventListener();
            mPresenceReference.addValueEventListener(mPresenceListener);
//            database.setPersistenceEnabled(true);
//            database.goOnline();

        }
        return database;
    }

    public void goOffline() {
        if (database != null) {
//            database.goOffline();
        }
    }
    public void goOnline() {
        if (database != null) {
//            database.goOnline();
        }
    }

    public void addPresenceListener (){
//        mPresenceReference.addValueEventListener(mPresenceListener);
    }

    public void removePresenceListener(){
//        mPresenceReference.removeEventListener(mPresenceListener);
    }
}
