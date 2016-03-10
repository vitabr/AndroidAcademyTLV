package com.aat.rntv;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Refael Ozeri on 05/03/2016.
 */
public class BaseApplication extends Application {

    private static BaseApplication singleton;
    public static BaseApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        singleton = this;

        Firebase.setAndroidContext(this);
    }


}
