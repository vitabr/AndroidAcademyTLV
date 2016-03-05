package com.aat.rntv.model;

import io.realm.RealmObject;

/**
 * Created by vito on 2/18/2016.
 */
public class Message extends RealmObject {

    private String mMessage;

    public Message(){}

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
