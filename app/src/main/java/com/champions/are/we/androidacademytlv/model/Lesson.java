package com.champions.are.we.androidacademytlv.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by vito on 2/18/2016.
 */
public class Lesson extends RealmObject {

    private String mTitle;
    private String mDescription;
    private String mLecturerUid;
    private Date mStartDate;

    public Lesson(){}

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmLecturerUid() {
        return mLecturerUid;
    }

    public void setmLecturerUid(String mLecturerUid) {
        this.mLecturerUid = mLecturerUid;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        this.mStartDate = startDate;
    }
}
