package com.aat.rntv.business;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.aat.rntv.BaseApplication;
import com.aat.rntv.business.receiver.OnAlarmReceiver;
import com.aat.rntv.model.Constants;
import com.aat.rntv.view.activities.LoginActivity;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Refael Ozeri on 05/03/2016.
 */
public class Utils implements Constants {

  /**
   * Logout the user, cleans shared prefs etc.
   */
  public static void logout(Context context) {

    if (FACEBOOK_PROVIDER_NAME.equals(SharedPref.getLoginProvider())) {
      FacebookSdk.sdkInitialize(BaseApplication.getInstance());
      LoginManager.getInstance().logOut();
    }

    SharedPref.userLogout();

    context.startActivity(LoginActivity.getIntent(context));
  }

  /** Create a new Alarm */
  public static void createAlarm(final long timeInMillis) {
    AlarmManager mgr = (AlarmManager) BaseApplication.getInstance().getSystemService(Context.ALARM_SERVICE);
    Intent i = new Intent(BaseApplication.getInstance(), OnAlarmReceiver.class);
    PendingIntent pi = PendingIntent.getBroadcast(BaseApplication.getInstance(), 0 , i, 0);
    mgr.set(AlarmManager.RTC_WAKEUP, timeInMillis, pi);
  }

  /** Cancel Alarm if there is any */
  public static void cancelAlarm() {
    AlarmManager mgr = (AlarmManager) BaseApplication.getInstance().getSystemService(Context.ALARM_SERVICE);
    Intent i = new Intent(BaseApplication.getInstance(), OnAlarmReceiver.class);
    PendingIntent pi = PendingIntent.getBroadcast(BaseApplication.getInstance(), 0 , i, 0);
    mgr.cancel(pi);
  }

  /**
   * Prints Key Hash that is used by Facebook.
   * @param context
   */
  public static void printKeyHashToLog(Context context) {
    try {
      PackageInfo info = context.getPackageManager().getPackageInfo(
          "com.aat.rntv",
          PackageManager.GET_SIGNATURES);
      for (Signature signature : info.signatures) {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(signature.toByteArray());
        Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
      }
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }
}
