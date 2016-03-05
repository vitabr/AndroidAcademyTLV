package com.champions.are.we.androidacademytlv.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by vito on 2/18/2016.
 */
public class Lesson extends RealmObject {

    private String mTitle;
    private Date mStartDate;

    public Lesson(){}

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(Date mStartDate) {
        this.mStartDate = mStartDate;
    }
}
