package com.aat.rntv.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.aat.rntv.BaseApplication;
import com.aat.rntv.business.Utils;
import com.aat.rntv.model.Lesson;
import com.aat.rntv.model.User;
import com.champions.are.we.androidacademytlv.R;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.shaded.fasterxml.jackson.databind.deser.Deserializers;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vito on 2/18/2016.
 */
public class SplashActivity extends Activity {

  private boolean mShouldPrintKeyHash = true;

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

    //TODO - Remove this after testing. logs out from facebook every entry.
    FacebookSdk.sdkInitialize(getApplicationContext());
    LoginManager.getInstance().logOut();

    if (mShouldPrintKeyHash) {
      Utils.printKeyHashToLog(this);
    }

    createDumpDB();

    new Timer().schedule(mTimerTask, 3000);
  }


  private void createDumpDB(){

    Realm myRealm = Realm.getInstance(this);

    RealmResults<User> results = myRealm.where(User.class).findAll();
    if(results != null && results.size() >0){
      Log.e("VITO", "Db records already exists.");
      return;
    }

    Log.e("VITO", "Creating dump records.");

    myRealm.beginTransaction();

    User user1 = myRealm.createObject(User.class);
    user1.setUid("user001");
    user1.setDisplayName("Vito Abramov");

    User user = myRealm.createObject(User.class);
    user.setUid("user002");
    user1.setDisplayName("Refael Ozeri");

    Lesson lesson = myRealm.createObject(Lesson.class);
    lesson.setmLecturerUid("user001");
    lesson.setmStartDate(new Date());
    lesson.setmTitle("Lecture + lab session. Bring your laptops with eclipse and android SDK working.");
    lesson.setmDescription("Lecture #1  (IDE instructions below)\n" +
            "\n" +
            "• Course and Team Overview\n" +
            "\n" +
            "• Android Overview - What is Android, Linux, layers... very high level\n" +
            "\n" +
            "• Show Eclipse and the perspective we can exploit\n" +
            "\n" +
            "• Application Fundamentals: Activity, Services, Broadcast, Content.\n" +
            "\n" +
            "• The Activity Class - Life Cycle\n" +
            "\n" +
            "• Eclipse project structure\n" +
            "\n" +
            "• Log class\n" +
            "\n" +
            "• Toast");

    Lesson lesson1 = myRealm.createObject(Lesson.class);
    lesson1.setmLecturerUid("user002");
    lesson1.setmStartDate(new Date());
    lesson1.setmTitle("Lecture #2: Views, Manifest, Permissions & Explicit Intent");
    lesson1.setmDescription("Lecture\n" +
            "\n" +
            "Part 1\n" +
            "\n" +
            "• AnyDon’t - Task app.\n" +
            "\n" +
            "• Introduction to Android framework.\n" +
            "\n" +
            "• Life Cycle - Context\n" +
            "\n" +
            "• Eclipse project structure.\n" +
            "\n" +
            "• Fragmentation\n" +
            "\n" +
            "• Main User Interface Classes - layouts,forms,etc\n" +
            "\n" +
            "• Manipulating Views - XML & Programmatically\n" +
            "\n" +
            "*************************LAB 1**************************\n" +
            "\n" +
            "Part 2\n" +
            "\n" +
            "• Adding an Activity/Service/ Content…..\n" +
            "\n" +
            "• Design Patterns\n" +
            "\n" +
            "• Adapter design pattern.\n" +
            "\n" +
            "• Explicit Intents.\n" +
            "\n" +
            "• Observer design pattern (Listener)\n" +
            "\n" +
            "*************************LAB 2************************** ");
    myRealm.commitTransaction();

  }

}
