package com.aat.rntv.view.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.champions.are.we.androidacademytlv.R;

/**
 * Created by vito on 2/18/2016.
 */
public class SetupActivity extends Activity implements View.OnClickListener {

  public static Intent getIntent(Context context){
    Intent intent = new Intent(context, SetupActivity.class);
    return intent;
  }

  private ImageButton mAvatar;
  private TextView mName;
  private TextView mEmail;
  private ImageButton mDesigner;
  private ImageButton mDeveloper;


  /****************************************************/
  /************ Activity lifecycle ********************/
  /****************************************************/

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setup);

    prepareView();
  }


  /****************************************************/
  /*****************   Utils   ************************/
  /****************************************************/

  /**
   * Prepare all references to UI layout objects
   */
  private void prepareView(){
    mAvatar = (ImageButton)findViewById(R.id.btnAvatar);
    mAvatar.setOnClickListener(this);

    mName = (TextView)findViewById(R.id.tvName);
    mEmail = (TextView)findViewById(R.id.tvEmail);

    mDesigner = (ImageButton)findViewById(R.id.btnDesigner);
    mDesigner.setOnClickListener(this);

    mDeveloper = (ImageButton)findViewById(R.id.btnDeveloper);
    mDeveloper.setOnClickListener(this);
  }

  /**
   * Save confirmed data to local database along with upload to Server
   * Should implement retry on failure due bad Internet connection etc.
   *
   * @param name
   * @param email
   * @param photoUri
   */
  private void saveAndUpload(String name, String email, Uri photoUri){

    // TODO implement save to local DB
    // TODO implement safe upload to Server
  }

  /****************************************************/
  /************ View.OnClickListener ******************/
  /****************************************************/

  @Override
  public void onClick(View v) {
    switch (v.getId()){

      case R.id.btnAvatar:
        // TODO implement image selection and upload selection to server
        break;

      case R.id.btnDeveloper:
        saveAndUpload(mName.getText().toString(), mEmail.getText().toString(), null);

        startAnimatedTransition(mDeveloper);
        break;

      case R.id.btnDesigner:
        saveAndUpload(mName.getText().toString(), mEmail.getText().toString(), null);

        startAnimatedTransition(mDesigner);
        break;

    }
  }

  private void startAnimatedTransition(View sharedView) {

    Intent intent;
    String transitionName;

    switch (sharedView.getId()){

      case R.id.btnDeveloper:
        intent = WelcomeActivity.getIntent(this, true);
        transitionName = getString(R.string.trn_developer);
        break;

      case R.id.btnDesigner:
      default:
        intent = WelcomeActivity.getIntent(this, false);
        transitionName = getString(R.string.trn_designer);
        break;
    }

    ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(SetupActivity.this, sharedView, transitionName);
    startActivity(intent, transitionActivityOptions.toBundle());
  }
}
