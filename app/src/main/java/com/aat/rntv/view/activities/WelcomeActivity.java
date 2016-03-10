package com.aat.rntv.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aat.rntv.business.SharedPref;
import com.champions.are.we.androidacademytlv.R;

/**
 * Created by vito on 2/18/2016.
 */
public class WelcomeActivity extends Activity implements View.OnClickListener {

  private static final String DEVELOPER = "Developer";
  private static final String DESIGNER = "Designer";

  private static final String TYPE = "IsDeveloper";

  public static Intent getIntent(Context context, boolean isDeveloper){
    Intent intent = new Intent(context, WelcomeActivity.class);
    intent.putExtra(TYPE, isDeveloper);
    return intent;
  }

  private Button mStart;
  private boolean mIsDeveloper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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

        String profession = mIsDeveloper ? DEVELOPER : DESIGNER;
        SharedPref.setProfession(profession);

        startActivity(MainActivity.getIntent(this));
        break;
    }
  }
}
