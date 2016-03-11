package com.aat.rntv.view.activities;

import android.app.Activity;
import android.os.Bundle;

import com.aat.rntv.business.SharedPref;
import com.aat.rntv.business.Utils;
import com.champions.are.we.androidacademytlv.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vito on 2/18/2016.
 */
public class SplashActivity extends Activity {

  private boolean mShouldPrintKeyHash = false;

  private TimerTask mTimerTask = new TimerTask() {
    @Override
    public void run() {
      String userId = SharedPref.getUserId();
      String profession = SharedPref.getProfession();

      startActivity(AboutActivity.getIntent(SplashActivity.this));
//      if (TextUtils.isEmpty(userId)) {
//        navigateToLogin();
//      } else if (TextUtils.isEmpty(profession)) {
//        navigateToSetup();
//      } else {
//        navigateToMainscreen();
//      }

      finish();
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    if (mShouldPrintKeyHash) {
      Utils.printKeyHashToLog(this);
    }

//    ((AnimationDrawable) ((ImageView)findViewById(R.id.ivBg)).getDrawable()).start();

    new Timer().schedule(mTimerTask, 3000);
  }

  private void navigateToLogin() {
    startActivity(LoginActivity.getIntent(this));
  }

  private void navigateToSetup() {
    startActivity(SetupActivity.getIntent(this));
  }

  private void navigateToMainscreen() {
    startActivity(MainActivity.getIntent(this));
  }

}
