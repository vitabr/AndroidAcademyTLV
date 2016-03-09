package com.aat.rntv.business;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.aat.rntv.BaseApplication;
import com.aat.rntv.model.Constants;
import com.champions.are.we.androidacademytlv.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Arrays;

/**
 * Created by Refael Ozeri on 05/03/2016.
 */
public class Backend implements Constants {

  //Checks if network is available
  private static boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }

  /**
   * Performs login with facebook, if successful, sends the access token to firebase.
   * @param activity
   * @param callback
   * @param manager
   */
  public static void performFacebookLogin(final Activity activity, final LoginCallback callback, final CallbackManager manager) {

    if (!isNetworkAvailable()) {
      callback.onError(activity.getString(R.string.no_internet));
      return;
    }

    LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "user_photos"));

    LoginManager.getInstance().registerCallback(manager, new FacebookCallback<LoginResult>() {
      @Override
      public void onSuccess(LoginResult loginResult) {
        Log.d("loginResult: ", loginResult.toString());
        onFacebookAccessTokenChange(loginResult.getAccessToken(), callback);
      }

      @Override
      public void onCancel() {
        callback.onError(activity.getString(R.string.facebook_login_cancelled));
      }

      @Override
      public void onError(FacebookException error) {
        callback.onError(activity.getString(R.string.facebook_login_cancelled) + ": " + error.getMessage());
      }
    });
  }

  private static void onFacebookAccessTokenChange(AccessToken token, final LoginCallback callback) {
    Firebase ref = new Firebase(FIREBASE_ADDRESS);

    if (token != null) {
      ref.authWithOAuthToken("facebook", token.getToken(), new Firebase.AuthResultHandler() {
        @Override
        public void onAuthenticated(AuthData authData) {
          // The Facebook user is now authenticated with our Firebase app
          String userId = authData.getUid();
          String email = (String) authData.getProviderData().get("email");
          String displayName = (String) authData.getProviderData().get("displayName");
          String profileImageURL = (String) authData.getProviderData().get("profileImageURL");
          String provider = authData.getProvider();

          SharedPref.setMyOwnUser(userId, email, displayName, profileImageURL, provider);

          callback.onSuccess("SUCCESS");
        }
        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
          // there was an error
          callback.onError(firebaseError.getMessage());
        }
      });
    } else {
      // Logged out of Facebook so do a logout from the Firebase app
      ref.unauth();
      callback.onError(BaseApplication.getInstance().getString(R.string.facebook_login_failed));
    }
  }

}
