package com.aat.rntv.business;

import android.app.Activity;
import android.util.Log;

import com.aat.rntv.model.Constants;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

/**
 * Created by Refael Ozeri on 05/03/2016.
 */
public class Backend implements Constants {

  public static void performFacebookLogin(Activity activity, final LoginCallback callback, CallbackManager manager) {

    LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "user_photos"));

    LoginManager.getInstance().registerCallback(manager, new FacebookCallback<LoginResult>() {
      @Override
      public void onSuccess(LoginResult loginResult) {

        Log.d("loginResult: ", loginResult.toString());
        Log.d("loginResult: ", loginResult.getAccessToken().toString());

        AccessToken accessToken = loginResult.getAccessToken();
        String socId = accessToken.getToken();
        String userId = accessToken.getUserId();

      }

      @Override
      public void onCancel() {
        callback.onError(FACEBOOK_CANCELLED);
      }

      @Override
      public void onError(FacebookException error) {
        callback.onError("Facebook Error: " + error.getMessage());
      }
    });
  }

}
