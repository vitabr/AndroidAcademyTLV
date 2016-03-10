package com.aat.rntv.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.aat.rntv.business.Utils;
import com.champions.are.we.androidacademytlv.R;

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
      Intent startIntent = LoginActivity.getIntent(getBaseContext());
      startActivity(startIntent);
      finish();
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // remove title
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_splash);

    if (mShouldPrintKeyHash) {
      Utils.printKeyHashToLog(this);
    }

    new Timer().schedule(mTimerTask, 3000);
  }

}
