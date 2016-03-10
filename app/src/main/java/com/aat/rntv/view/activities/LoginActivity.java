package com.aat.rntv.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aat.rntv.BaseApplication;
import com.aat.rntv.business.Backend;
import com.aat.rntv.business.LoginCallback;
import com.champions.are.we.androidacademytlv.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

/**
 * Created by vito on 2/18/2016.
 */
public class LoginActivity extends Activity implements View.OnClickListener, LoginCallback {

  public static Intent getIntent(Context context){
    Intent intent = new Intent(context, LoginActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    return intent;
  }

  private CallbackManager mCallbackManager;
  private Button          mLoginGoogle;
  private Button          mLoginFacebook;
  private Button          mSkip;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setStatusBarColor(Color.TRANSPARENT);
    FacebookSdk.sdkInitialize(BaseApplication.getInstance());
    mCallbackManager = CallbackManager.Factory.create();
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
        //TODO implement google login
        break;
      case R.id.btnFacebook:
        Backend.performFacebookLogin(LoginActivity.this, LoginActivity.this, mCallbackManager);
        break;
      case R.id.btnSkip:
        startActivity(SetupActivity.getIntent(this));
        break;
    }
  }

  @Override
  public void onSuccess(String response) {
    startActivity(SetupActivity.getIntent(this));
  }

  @Override
  public void onError(String e) {
    Toast.makeText(LoginActivity.this, e, Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    mCallbackManager.onActivityResult(requestCode, resultCode, data);
    Log.d("callbackManager ", mCallbackManager.toString());
    Log.d("callbackManager ", requestCode+"");
    Log.d("callbackManager ", resultCode + "");
    Log.d("callbackManager ", data.toString());
  }
}
