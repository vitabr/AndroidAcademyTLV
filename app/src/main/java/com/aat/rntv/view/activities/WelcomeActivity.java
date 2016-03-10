package com.aat.rntv.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.champions.are.we.androidacademytlv.R;

/**
 * Created by vito on 2/18/2016.
 */
public class WelcomeActivity extends Activity implements View.OnClickListener {

  private static final String TYPE = "IsDeveloper";
  public static Intent getIntent(Context context, boolean isDeveloper){
    Intent intent = new Intent(context, WelcomeActivity.class);
    intent.putExtra(TYPE, isDeveloper);
    return intent;
  }

  private Button mStart;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // remove title
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
    mIsDeveloper = getIntent().getBooleanExtra(TYPE,true);
    if(mIsDeveloper){
      setContentView(R.layout.activity_welcome_developer);
    }else{
      setContentView(R.layout.activity_welcome_designer);
    }

    prepareView();
  }

  private void prepareView(){

    mStart = (Button)findViewById(R.id.btnStart);
    mStart.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.btnStart:
        startActivity(MainActivity.getIntent(this));
        break;
    }
  }
}
