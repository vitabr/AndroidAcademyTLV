package com.champions.are.we.androidacademytlv.model;

import io.realm.RealmObject;

/**
 * Created by vito on 2/18/2016.
 */
public class User extends RealmObject {

    private String mUid;
    private String mFirstName;
    private String mLastName;
    private String mGender;
    private String mPhotoURL;

    public User(){}

    public String getmUid() {
        return mUid;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public String getmPhotoURL() {
        return mPhotoURL;
    }

    public void setmPhotoURL(String mPhotoURL) {
        this.mPhotoURL = mPhotoURL;
    }
}
