package com.aat.rntv.model;

import com.shaded.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Refael Ozeri on 3/10/16.
 */
@JsonIgnoreProperties({"mAttendants"})
public class FirebaseLesson {

    private String mTitle;
    private String mDescription;
    private String mLecturerName;
    private String mStartDate;
    private String mBg;

    public FirebaseLesson() {
        // empty default constructor, necessary for firebase to be able to deserialize lessons.
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmLecturerName() {
        return mLecturerName;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public String getmBg() {
        return mBg;
    }
}
