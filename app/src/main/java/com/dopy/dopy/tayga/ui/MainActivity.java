package com.dopy.dopy.tayga.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityMainBinding;
import com.dopy.dopy.tayga.databinding.AppBarMainBinding;
import com.dopy.dopy.tayga.databinding.ContentMainBinding;

import static android.R.attr.fragment;

/*
* */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    ActivityMainBinding activityBinding;
    AppBarMainBinding appbarBinding;
    ContentMainBinding contentBinding;

    boolean backKeyPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        appbarBinding = activityBinding.appBarMain;
        contentBinding = appbarBinding.contentMain;
        setSupportActionBar(appbarBinding.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, activityBinding.drawerLayout, appbarBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        activityBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int fragmentCountget=getSupportFragmentManager().getBackStackEntryCount();
        Log.d("MainActivity","fragmentCountget -> "+fragmentCountget);
        if(fragmentCountget>0){
            getSupportFragmentManager().popBackStack();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(contentBinding.mainFrame.getId(),MainFragment.newInstance())
                    .addToBackStack(null).commit();
        }

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_main) {
            getSupportFragmentManager().beginTransaction()
                    .replace(contentBinding.mainFrame.getId(),MainFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.menu_game) {
            getSupportFragmentManager().beginTransaction()
                    .replace(contentBinding.mainFrame.getId(),GameFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.menu_favorites) {
            getSupportFragmentManager().beginTransaction()
                    .replace(contentBinding.mainFrame.getId(),MainFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.menu_remove_favorites) {

        } else if (id == R.id.menu_profile_detail) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (activityBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            activityBinding.drawerLayout.closeDrawer(GravityCompat.START);}
        else if (backKeyPressed) {
            finish();
        } else {
            Toast.makeText(this, "뒤로가기를 한번 더 누르시면 앱을 종료합니다.", Toast.LENGTH_LONG).show();
            backKeyPressed = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backKeyPressed = false;
                }
            }, 3 * 1000);
        }
    }
}
