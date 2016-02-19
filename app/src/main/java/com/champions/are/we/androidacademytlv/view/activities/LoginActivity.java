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
    mLoginGoogle = (Button)findViewById(R.id.btnGoogle);
    mLoginGoogle.setOnClickListener(this);

    mLoginFacebook = (Button)findViewById(R.id.btnFacebook);
    mLoginFacebook.setOnClickListener(this);

    mSkip = (Button)findViewById(R.id.btnSkip);
    mSkip.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.btnGoogle:
      case R.id.btnFacebook:
        // TODO implement login action
        break;
      case R.id.btnSkip:
        startActivity(SetupActivity.getIntent(this));
        break;
    }
  }
}
