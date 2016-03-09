package com.aat.rntv.model;

import io.realm.RealmObject;

/**
 * Created by vito on 2/18/2016.
 */
public class User extends RealmObject {

  private String mUid;
  private String mDisplayName;
  private String mGender;
  private String mPhotoURL;

  public String getUid() {
    return mUid;
  }

  public void setUid(String mUid) {
    this.mUid = mUid;
  }

  public String getDisplayName() {
    return mDisplayName;
  }

  public void setDisplayName(String mDisplayName) {
    this.mDisplayName = mDisplayName;
  }

  public String getGender() {
      return mGender;
  }

  public void setGender(String mGender) {
    this.mGender = mGender;
  }

  public String getPhotoURL() {
    return mPhotoURL;
  }

  public void setPhotoURL(String mPhotoURL) {
    this.mPhotoURL = mPhotoURL;
  }
}
