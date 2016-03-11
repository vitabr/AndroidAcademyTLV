package com.aat.rntv.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.TextUtils;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aat.rntv.business.SharedPref;
import com.aat.rntv.business.Utils;
import com.aat.rntv.model.Constants;
import com.aat.rntv.model.Lesson;
import com.aat.rntv.model.User;
import com.aat.rntv.view.fragments.LessonsFragment;
import com.aat.rntv.view.fragments.MainFragment;
import com.aat.rntv.view.view_utils.CircleTransform;
import com.champions.are.we.androidacademytlv.R;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends FragmentActivity
  implements NavigationView.OnNavigationItemSelectedListener, Constants {

  private DrawerLayout mDrawerLayout;
  private ActionBarDrawerToggle mDrawerToggle;
  private ViewPager mViewPager;
  private NavigationView mNavigationView;

  public static Intent getIntent(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Realm realm = Realm.getInstance(this);

    printOutDb(realm);

    setupWindowAnimations();

    setupUI();

  }

  private void printOutDb(Realm realm) {

    Log.e("VITO", "Start print out:");
    RealmResults<User> results = realm.where(User.class).findAll();

    for (User c : results) {
//      Log.e("VITO", c.);
    }

    RealmResults<Lesson> results1 = realm.where(Lesson.class).findAll();

    for(Lesson c:results1) {
      Log.e("VITO", c.getmTitle());
    }
    Log.e("VITO", "End print out.");
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    switch (item.getItemId()) {
      case R.id.nav_camera:
        break;
      case R.id.nav_gallery:
        break;
      case R.id.nav_slideshow:
        break;
      case R.id.nav_manage:
        break;
      case R.id.nav_share:
        break;
      case R.id.nav_send:
        break;
      case R.id.nav_logout:
        Utils.logout(MainActivity.this);
        break;
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  /***************************************************/
  /*** Inner classes: Fragments,Adapters etc. ********/
  /***************************************************/
  private class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int i) {

      Fragment fragment = null;
      switch (i){
        case 0:
          fragment = new MainFragment();
          break;
        case 1:
          fragment = new LessonsFragment();
          break;
      }
      return fragment;
    }

    @Override
    public int getCount() {
      return 2;
    }

    @Override
    public CharSequence getPageTitle(int i) {
      switch (i) {
        case 0:
          return null;//"Main";

        case 1:
          return null;//"Favorites";

        default:
          return "You fucked Up!";
      }
    }
  }

  /***************************************************/
  /*** UI setup functions ****************************/
  /***************************************************/

  /**
   * Setup enter/exit animation
   */
  private void setupWindowAnimations() {
    Fade fade = new Fade();
    fade.setDuration(1000);
    getWindow().setEnterTransition(fade);

    // No exit animation for main screen.
    getWindow().setReturnTransition(null);
  }

  private void setupUI(){
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    setupNavigationView();
    setupTabs();
    setupViewPager();
    fillUserInfo();
    setupFAB();
  }

  private void setupTabs() {
  }

  private void fillUserInfo(){
    String photoURL = SharedPref.getProfileImageURL();
    if(TextUtils.isEmpty(photoURL)){
      return;
    }

    ((TextView)mNavigationView.getHeaderView(0).findViewById(R.id.tvName)).setText(SharedPref.getDisplayName());
    ((TextView)mNavigationView.getHeaderView(0).findViewById(R.id.tvEmail)).setText(SharedPref.getEmail());

    Picasso.with(this)
            .load(SharedPref.getProfileImageURL())
            .placeholder(R.drawable.ic_avatar_man)
            .transform(new CircleTransform())
            .into(((ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.imageView)));
  }

  private void setupViewPager(){
    View btnDrawer = findViewById(R.id.btn_drawer);
    btnDrawer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mDrawerLayout.openDrawer(GravityCompat.START);
      }
    });

    TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    final ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    tabLayout.setTabsFromPagerAdapter(pagerAdapter);
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    tabLayout.getTabAt(0).setIcon(R.drawable.ic_homenavbar);
    tabLayout.getTabAt(1).setIcon(R.drawable.ic_lecturesnavbar);
    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));

    mViewPager = (ViewPager) findViewById(R.id.pager);
    mViewPager.setAdapter(pagerAdapter);
    mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });

  }

  private void setupNavigationView() {
    //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //setSupportActionBar(toolbar);


//    mDrawerToggle = new ActionBarDrawerToggle(
//        this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//    mDrawerLayout.setDrawerListener(mDrawerToggle);
//    mDrawerToggle.syncState();

//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
//        R.string.app_name);
//
//    mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//    mDrawerToggle.syncState();

    mNavigationView = (NavigationView) findViewById(R.id.nav_view);
    mNavigationView.setNavigationItemSelectedListener(this);
  }

  private void setupFAB() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
  }


}
