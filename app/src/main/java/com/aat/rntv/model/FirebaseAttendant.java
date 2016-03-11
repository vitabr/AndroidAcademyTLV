package com.aat.rntv.model;

import com.shaded.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Refael Ozeri on 3/11/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class FirebaseAttendant {

    private String mImageURL;
    private String mDisplayName;

    public FirebaseAttendant() {}

    public FirebaseAttendant(String mImageURL, String mDisplayName) {
        this.mImageURL = mImageURL;
        this.mDisplayName = mDisplayName;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public String getmDisplayName() {
        return mDisplayName;
    }
}