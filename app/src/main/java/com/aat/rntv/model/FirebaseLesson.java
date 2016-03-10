package com.aat.rntv.model;

/**
 * Created by Refael Ozeri on 10/03/2016.
 */

public class FirebaseLesson {
  private String mTitle;
  private String mDescription;
  private String mLecturerUid;
  private String mStartDate;

  public FirebaseLesson() {
  // empty default constructor, necessary for firebase to be able to deserialize lessons.
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
