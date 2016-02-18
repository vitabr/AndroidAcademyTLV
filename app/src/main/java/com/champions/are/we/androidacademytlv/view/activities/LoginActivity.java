package com.champions.are.we.androidacademytlv.view.activities;

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
public class LoginActivity extends Activity implements View.OnClickListener {

  public static Intent getIntent(Context context){
    Intent intent = new Intent(context, LoginActivity.class);
    return intent;
  }

  private Button mLoginGoogle;
  private Button mLoginFacebook;
  private Button mSkip;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    prepareView();
  }

  private void prepareView(){
    mLoginGoogle = (Button)findViewById(R.id.btn_google);
    mLoginGoogle.setOnClickListener(this);

    mLoginFacebook = (Button)findViewById(R.id.btn_facebook);
    mLoginFacebook.setOnClickListener(this);

    mSkip = (Button)findViewById(R.id.btn_skip);
    mSkip.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.btn_google:
      case R.id.btn_facebook:
      case R.id.btn_skip:

        // TODO implement login action
        break;
    }
  }
}
