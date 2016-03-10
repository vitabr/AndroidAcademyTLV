package com.aat.rntv.model;

import io.realm.RealmObject;

/**
 * Created by Refael Ozeri on 3/10/16.
 */
public class Lesson extends RealmObject {

    private String mTitle;
    private String mDescription;
    private String mLecturerUid;
    private String mStartDate;

    public Lesson() {
        // empty default constructor, necessary for firebase to be able to deserialize lessons.
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmLecturerUid(String mLecturerUid) {
        this.mLecturerUid = mLecturerUid;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmLecturerUid() {
        return mLecturerUid;
    }

    public String getmStartDate() {
        return mStartDate;
    }

}
