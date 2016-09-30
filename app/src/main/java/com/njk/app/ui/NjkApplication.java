package com.njk.app.ui;

import android.app.Application;
import android.content.Context;

/**
 * Created by meher on 30/9/16.
 */

public class NjkApplication extends Application {

    public static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
    }
}
