package com.aat.rntv.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.aat.rntv.BaseApplication;
import com.aat.rntv.business.Utils;
import com.champions.are.we.androidacademytlv.R;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.shaded.fasterxml.jackson.databind.deser.Deserializers;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vito on 2/18/2016.
 */
public class SplashActivity extends Activity {

  private boolean mShouldPrintKeyHash = true;

  private TimerTask mTimerTask = new TimerTask() {
    @Override
    public void run() {
      //Intent startIntent = MainActivity.getIntent(getBaseContext());
      Intent startIntent = LoginActivity.getIntent(getBaseContext());
      startActivity(startIntent);
      finish();
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    //TODO - Remove this after testing. logs out from facebook every entry.
    FacebookSdk.sdkInitialize(getApplicationContext());
    LoginManager.getInstance().logOut();

    if (mShouldPrintKeyHash) {
      Utils.printKeyHashToLog(this);
    }

    new Timer().schedule(mTimerTask, 3000);
  }




}
