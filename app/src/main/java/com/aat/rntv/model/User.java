package com.aat.rntv.model;

import io.realm.RealmObject;

/**
 * Created by vito on 2/18/2016.
 */
public class User extends RealmObject {

  private String mUid;
  private String mEmail;
  private String mDisplayName;
  private String mPhotoURL;

  public User(){}

  public String getmUid() {
    return mUid;
  }

  public void setmUid(String mUid) {
    this.mUid = mUid;
  }

  public String getmEmail() {
    return mEmail;
  }

  public void setmEmail(String mEmail) {
    this.mEmail = mEmail;
  }

  public String getmDisplayName() {
    return mDisplayName;
  }

  public void setmDisplayName(String mDisplayName) {
    this.mDisplayName = mDisplayName;
  }

  public String getmPhotoURL() {
    return mPhotoURL;
  }

  public void setmPhotoURL(String mPhotoURL) {
    this.mPhotoURL = mPhotoURL;
  }
}