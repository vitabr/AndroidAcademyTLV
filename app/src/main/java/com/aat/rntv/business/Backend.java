package com.aat.rntv.business;

import android.app.Activity;
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


  public static void performFacebookLogin(final Activity activity, final LoginCallback callback, final CallbackManager manager) {

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
          callback.onSuccess("SUCCESS"); //TODO (Refael) - continue here.
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
