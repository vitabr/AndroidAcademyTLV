package com.champions.are.we.androidacademytlv;

import android.app.Application;
import android.content.Context;

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

        Firebase.setAndroidContext(this);
        singleton = this;
    }

}
