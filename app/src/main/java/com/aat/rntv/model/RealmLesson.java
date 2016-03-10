package com.aat.rntv.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by vito on 2/18/2016.
 */
public class RealmLesson extends RealmObject {

    private String mTitle;
    private String mDescription;
    private String mLecturerUid;
    private Date mStartDate;

    public RealmLesson(){}

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String title) {
        this.mTitle = title;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String description) {
        this.mDescription = description;
    }

    public String getmLecturerUid() {
        return mLecturerUid;
    }

    public void setmLecturerUid(String lecturerUid) {
        this.mLecturerUid = lecturerUid;
    }

    public Date getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(Date startDate) {
        this.mStartDate = startDate;
    }
}
