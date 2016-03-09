package com.aat.rntv.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

import com.aat.rntv.model.Lesson;
import com.aat.rntv.model.User;
import com.champions.are.we.androidacademytlv.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {


  private DrawerLayout mDrawerLayout;
  private ActionBarDrawerToggle mDrawerToggle;

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

//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);

//    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//    fab.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//      }
//    });
//
//    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//    drawer.setDrawerListener(toggle);
//    toggle.syncState();
//
//    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//    navigationView.setNavigationItemSelectedListener(this);

    printOutDb();
    setupWindowAnimations();

    setupUI();

  }

  private void printOutDb(){


    Log.e("VITO", "Start print out:");
    RealmResults<User> results = mRealm.where(User.class).findAll();

    for(User c:results) {
      Log.e("VITO", c.getDisplayName());
    }

    RealmResults<Lesson> results1 = mRealm.where(Lesson.class).findAll();

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
    setupToolbar();
    setupTabs();
    setupFAB();
  }

  private void setupTabs(){
    TabHost host = (TabHost)findViewById(android.R.id.tabhost);
    host.setup();

    TabHost.TabSpec spec = host.newTabSpec("Classes");
    spec.setIndicator("Classes", getDrawable(R.drawable.tab_icon_selector));
    spec.setContent(R.id.tab_classes);
    host.addTab(spec);

    spec = host.newTabSpec("Favorites");
    spec.setContent(R.id.tab_favorites);
    spec.setIndicator("Favorites", getDrawable(R.drawable.tab_icon_selector));
    host.addTab(spec);

    spec = host.newTabSpec("Pick Ups");
    spec.setContent(R.id.tab_pickups);
    spec.setIndicator("Pick Ups", getDrawable(R.drawable.tab_icon_selector));
    host.addTab(spec);

    spec = host.newTabSpec("RSVP");
    spec.setContent(R.id.tab_rsvp);
    spec.setIndicator("RSVP", getDrawable(R.drawable.tab_icon_selector));
    host.addTab(spec);

    // Drawer toggle button visually placed on tab bar.
    // So we initialize it here
    findViewById(R.id.btnDrawer).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
          mDrawerLayout.closeDrawers();
        }else{
          mDrawerLayout.openDrawer(GravityCompat.START);
        }
      }
    });
  }

  private void setupToolbar() {
    //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //setSupportActionBar(toolbar);

    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mDrawerToggle = new ActionBarDrawerToggle(
        this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    mDrawerLayout.setDrawerListener(mDrawerToggle);
    mDrawerToggle.syncState();

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
