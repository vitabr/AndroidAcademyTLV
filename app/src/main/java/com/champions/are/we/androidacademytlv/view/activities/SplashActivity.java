package com.champions.are.we.androidacademytlv.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.champions.are.we.androidacademytlv.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vito on 2/18/2016.
 */
public class SplashActivity extends Activity {

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

    new Timer().schedule(mTimerTask, 3000);
  }


}
