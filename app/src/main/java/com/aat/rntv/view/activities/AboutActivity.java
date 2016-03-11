package com.aat.rntv.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.aat.rntv.view.fragments.AboutUsFragment;
import com.champions.are.we.androidacademytlv.R;

/**
 * Created by vito on 2/18/2016.
 */
public class AboutActivity extends FragmentActivity {

  private ViewPager mViewPager;

  public static Intent getIntent(Context context){
    Intent intent = new Intent(context, AboutActivity.class);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about);

    setupViewPager();
  }

  private void setupViewPager(){
    final ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    mViewPager = (ViewPager) findViewById(R.id.about_us_pager);
    mViewPager.setAdapter(pagerAdapter);
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
          bundle.putInt("image", R.drawable.img_aboutusall);
          fragment.setArguments(bundle);
          break;
        case 1:
          fragment = new AboutUsFragment();
          bundle.putInt("image", R.drawable.img_natalieb);
          fragment.setArguments(bundle);
          break;
        case 2:
          fragment = new AboutUsFragment();
          bundle.putInt("image", R.drawable.img_refaelb);
          fragment.setArguments(bundle);
          break;
        case 3:
          fragment = new AboutUsFragment();
          bundle.putInt("image", R.drawable.img_timorb);
          fragment.setArguments(bundle);
          break;
        case 4:
          fragment = new AboutUsFragment();
          bundle.putInt("image", R.drawable.img_vitob);
          fragment.setArguments(bundle);
          break;
      }
      return fragment;
    }

    @Override
    public int getCount() {
      return 5;
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
