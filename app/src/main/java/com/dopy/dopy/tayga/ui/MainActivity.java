package com.dopy.dopy.tayga.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityMainBinding;
import com.dopy.dopy.tayga.databinding.AppBarMainBinding;
import com.dopy.dopy.tayga.databinding.ContentMainBinding;

/*
* 여기서 구현된 lib은 Side-Menu.Android 라이브러리로 navigation drawer의 레이아웃을 바꿔주는 라이브러리를 구현했다.
* https://github.com/Yalantis/Side-Menu.Android
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

        getSupportFragmentManager().beginTransaction().replace(contentBinding.mainFrame.getId(),MainFragment.newInstance()).commit();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_main) {
            getSupportFragmentManager().beginTransaction().replace(contentBinding.mainFrame.getId(),MainFragment.newInstance()).commit();
        } else if (id == R.id.menu_game) {
            getSupportFragmentManager().beginTransaction().replace(contentBinding.mainFrame.getId(),MainFragment.newInstance()).commit();
        } else if (id == R.id.menu_favorites) {
            getSupportFragmentManager().beginTransaction().replace(contentBinding.mainFrame.getId(),MainFragment.newInstance()).commit();
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
