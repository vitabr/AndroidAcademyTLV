package com.aat.rntv.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aat.rntv.view.fragments.AboutUsFragment;
import com.aat.rntv.view.fragments.LessonsFragment;
import com.champions.are.we.androidacademytlv.R;

/**
 * Created by vito on 2/18/2016.
 */
public class AboutActivity extends Activity {

  public static Intent getIntent(Context context){
    Intent intent = new Intent(context, AboutActivity.class);
    return intent;
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about);


  }

  private class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int i) {

      Fragment fragment = null;
      Bundle bundle = new Bundle();
      switch (i){
        case 0:
          fragment = new AboutUsFragment();
//          bundle.putInt("image", R.id.a);
          fragment.setArguments(bundle);
          break;
        case 1:
          fragment = new LessonsFragment();
          break;
        case 2:
          fragment = new LessonsFragment();
          break;
        case 3:
          fragment = new LessonsFragment();
          break;
      }
      return fragment;
    }

    @Override
    public int getCount() {
      return 4;
    }

    @Override
    public CharSequence getPageTitle(int i) {
//      switch (i) {
//        case 0:
//          return null;//"Main";
//
//        case 1:
          return null;//"Favorites";
//
//        default:
//          return "You fucked Up!";
//      }
    }
  }
}
