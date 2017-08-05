package com.dopy.dopy.tayga.mainActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TabHost;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityMainBinding;
import com.dopy.dopy.tayga.databinding.AppBarMainBinding;
import com.dopy.dopy.tayga.databinding.ContentMainBinding;

import static android.R.id.toggle;
import static com.dopy.dopy.tayga.R.id.toolbar;
/*
* 메인 액티비티에서 프래그먼트로 여러 인기순의 방송들을 정렬
* */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding activityMainBinding;
    AppBarMainBinding appBarMainBinding;
    ContentMainBinding contentMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        데이터 바인딩
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        appBarMainBinding = DataBindingUtil.findBinding(activityMainBinding.appBarMain.getRoot());
        contentMainBinding = DataBindingUtil.findBinding(appBarMainBinding.contentMain.getRoot());

//       네비게이션 드로워의 동작을 바인딩
        Toolbar toolbar = appBarMainBinding.toolbar;
        setSupportActionBar(toolbar);
        DrawerLayout drawer = activityMainBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        네비게이션 드로워에 클릭 리스너 바인딩
        activityMainBinding.navView.setNavigationItemSelectedListener(this);

//        커스텀 탭 추가  후 이미지 연결
        TabLayout tabLayout = contentMainBinding.tabHostLayout;
        View view1 = getLayoutInflater().inflate(R.layout.customtab,null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.tiwich_logo);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view1));
        View view2 = getLayoutInflater().inflate(R.layout.customtab,null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.africatv_logo);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view2));
        View view3 = getLayoutInflater().inflate(R.layout.customtab,null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.youtube_logo);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view3));

//        뷰 페이저에 연결
        final ViewPager viewPager = contentMainBinding.mainViewPager;
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(),3);
        viewPager.setAdapter(tabPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

//      클릭 리스터 연결
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

//    백 버튼을 눌렀을 때 네비게이션 드로워가 닫히도록 하기위해서
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = activityMainBinding.drawerLayout;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    드로워 레이아웃에 메뉴 xml을 그려준다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    선택 되었을 때 동작할 리스너
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

        DrawerLayout drawer = activityMainBinding.drawerLayout;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
