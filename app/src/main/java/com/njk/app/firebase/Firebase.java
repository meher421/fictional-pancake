package com.njk.app.firebase;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by meher on 12/9/16.
 */

public class Firebase {

    private static FirebaseDatabase database;

    private Firebase() {}


    public static FirebaseDatabase getInstance() {
        if (database == null) {
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }
        return database;
    }
}
