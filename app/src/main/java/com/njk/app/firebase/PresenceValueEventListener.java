package com.njk.app.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.njk.app.ui.DownlinkIntentService;
import com.njk.app.ui.NjkApplication;
import com.njk.app.utils.Logger;

/**
 * Created by meher on 19/10/16.
 */

public class PresenceValueEventListener implements ValueEventListener {
    private final String TAG = "123456-PresenceValueEventListener";

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        boolean connected = dataSnapshot.getValue(Boolean.class);
        if (connected) {
            Logger.e(TAG, "connected");
            DownlinkIntentService.startActionDataInit(NjkApplication.mAppContext);
        } else {
            Logger.e(TAG, "not connected");
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Logger.e(TAG, "Listener was cancelled");

    }
}
