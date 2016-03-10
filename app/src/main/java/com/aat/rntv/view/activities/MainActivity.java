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
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aat.rntv.model.Constants;
import com.aat.rntv.model.FirebaseLesson;
import com.aat.rntv.model.RealmLesson;
import com.aat.rntv.model.User;
import com.aat.rntv.view.fragments.FavoritesFragment;
import com.aat.rntv.view.fragments.MainFragment;
import com.aat.rntv.view.fragments.PickUpsFragment;
import com.aat.rntv.view.fragments.RcvpFragment;
import com.champions.are.we.androidacademytlv.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends FragmentActivity
  implements NavigationView.OnNavigationItemSelectedListener, Constants {

  private DrawerLayout mDrawerLayout;
  private ActionBarDrawerToggle mDrawerToggle;
  private ViewPager mViewPager;
  private ArrayList<FirebaseLesson> mLessonsList;

  public static Intent getIntent(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    return intent;
  }

  private Realm mRealm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mRealm = Realm.getInstance(this);

    printOutDb();

    setupWindowAnimations();

    setupUI();

    loadData();
  }

  private void loadData() {

    mLessonsList = new ArrayList<>();

    final Firebase firebase = new Firebase(FIREBASE_ADDRESS + FIREBASE_LESSONS);
    firebase.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        System.out.println("There are " + snapshot.getChildrenCount() + " lessons");
        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
          FirebaseLesson lesson = postSnapshot.getValue(FirebaseLesson.class);
          mLessonsList.add(lesson);
        }
      }

      @Override
      public void onCancelled(FirebaseError firebaseError) {
        System.out.println("The read failed: " + firebaseError.getMessage());
      }
    });
  }

  private void printOutDb(){

    Log.e("VITO", "Start print out:");
    RealmResults<User> results = mRealm.where(User.class).findAll();

    for(User c:results) {
//      Log.e("VITO", c.getmFirstName());
    }

    RealmResults<RealmLesson> results1 = mRealm.where(RealmLesson.class).findAll();

    for(RealmLesson c:results1) {
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
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

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
          fragment = new FavoritesFragment();
          break;
        case 2:
          fragment = new PickUpsFragment();
          break;
        case 3:
          fragment = new RcvpFragment();
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
      switch (i) {
        case 0:
          return "Main";

        case 1:
          return "Favorites";

        case 2:
          return "PickUps";

        case 3:
          return "RCVP";

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
    setupViewPager();
    setupFAB();
  }

  private void setupViewPager(){
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    final ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    tabLayout.setTabsFromPagerAdapter(pagerAdapter);
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

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

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
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
