package com.aat.rntv.business;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.aat.rntv.BaseApplication;

/**
 * Created by Refael Ozeri on 09/03/2016.
 */
public class SharedPref {

  //initialize the shared pref.
  private static SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance());

  public static void saveMyOwnUser(String userId, String email, String displayName, String profileImageURL, String provider) {
    mSharedPreferences.edit().putString("userId", userId).apply();
    mSharedPreferences.edit().putString("email", email).apply();
    mSharedPreferences.edit().putString("displayName", displayName).apply();
    mSharedPreferences.edit().putString("profileImageURL", profileImageURL).apply();
    mSharedPreferences.edit().putString("provider", provider).apply();
  }

  public static void userLogout() {
    mSharedPreferences.edit().remove("userId").apply();
    mSharedPreferences.edit().remove("email").apply();
    mSharedPreferences.edit().remove("displayName").apply();
    mSharedPreferences.edit().remove("profileImageURL").apply();
    mSharedPreferences.edit().remove("provider").apply();
  }

  public static String getUserId() {
    return mSharedPreferences.getString("userId", "");
  }

  public static String getEmail() {
    return mSharedPreferences.getString("email", "");
  }

  public static String getDisplayName() {
    return mSharedPreferences.getString("displayName", "");
  }

  public static String getProfileImageURL() {
    return mSharedPreferences.getString("profileImageURL", "");
  }

  public static String getLoginProvider() {
    return mSharedPreferences.getString("provider", "");
  }
}
