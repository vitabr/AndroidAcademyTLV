package com.champions.are.we.androidacademytlv.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.champions.are.we.androidacademytlv.R;

/**
 * Created by vito on 2/18/2016.
 */
public class SplashActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    setContentView(R.layout.activity_splash);
  }
}
