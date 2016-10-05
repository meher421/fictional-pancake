package com.njk.app.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.myapplication.R;
import com.njk.app.utils.Logger;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private String TAG = "MainActivity-123456";
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hearder = navigationView.getHeaderView(0);
        ImageView imageView = (ImageView) hearder.findViewById(R.id.imageView);
        imageView.setOnClickListener(this);

        getFragmentManager().beginTransaction().replace(R.id.frame, new SplashFragment(), "splash").commit();

    }

    public void startHomeFragment() {
        getFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment(), "home").commitAllowingStateLoss();
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
//        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fg = getFragmentManager().findFragmentByTag(item.getTitle().toString());
        if (fg != null) {
            Logger.i(TAG, "onNav added: " + fg.isAdded() + " detached: " + fg.isDetached() + " hidden: " + fg.isHidden() + " inLayout: " + fg.isInLayout() + " visible :" + fg.isVisible());
            if (fg.isAdded()) {

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        }

        Fragment fragment = null;

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_notifications) {
            fragment = new NotificationsFragment();
        } else if (id == R.id.nav_about_us) {
            fragment = new AboutUsFragment();

        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();
        } else if (id == R.id.nav_contact_us) {
            fragment = new ContactUsFragment();
        }

        getFragmentManager().beginTransaction().replace(R.id.frame, fragment, item.getTitle().toString()).commit();
        Logger.i(TAG, "adding fragment :" + item.getTitle().toString());


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        if (R.id.imageView == v.getId()) {

//            getFragmentManager().beginTransaction().replace(R.id.frame, SigninFragment.newInstance(), "signin").commit();
//            mDrawerLayout.closeDrawer(GravityCompat.START);
        }

    }
}
