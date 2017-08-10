package com.dopy.dopy.tayga;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.dopy.dopy.tayga.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;

/*
* 여기서 구현된 lib은 Side-Menu.Android 라이브러리로 navigation drawer의 레이아웃을 바꿔주는 라이브러리를 구현했다.
* https://github.com/Yalantis/Side-Menu.Android
* */
public class MainActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    public static final String CLOSE = "Close";
    public static final String MAINFRAGMENT = "mainpragment";
    public static final String FAVORITES = "favorites";

    ActivityMainBinding binding;
    MainFragment mainFragment;
    ActionBarDrawerToggle drawerToggle;
    List<SlideMenuItem> list = new ArrayList<>();
    ViewAnimator viewAnimator; // 프레그 먼트 화면이 전환될 때 물결무늬가 발생하는 에니메이션

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainFragment = MainFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mainFragment)
                .commit();

        createMenuList();
        setActionBar();
        viewAnimator = new ViewAnimator<>(this, list, mainFragment, binding.drawerLayout, this);
    }

    //    navigation drawer에 아이콘 이미지를 추가한다.
    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(MainActivity.CLOSE, R.drawable.ic_cancel_black_24dp);
        list.add(menuItem0);
        SlideMenuItem menuItem1 = new SlideMenuItem(MainActivity.MAINFRAGMENT, R.drawable.ic_live_tv_black_24dp); //first parameter is the id of menu item,the second is the icon resouce
        list.add(menuItem1);
        SlideMenuItem menuItem2 = new SlideMenuItem(MainActivity.FAVORITES, R.drawable.ic_local_play_black_24dp);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem("", R.drawable.ic_chrome_reader_mode_black_24dp);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem("", R.drawable.ic_favorite_black_24dp);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem("", R.drawable.ic_perm_contact_calendar_black_24dp);
        list.add(menuItem5);
    }

    private void setActionBar() {
        binding.drawerLayout.setScrimColor(Color.TRANSPARENT);
        binding.leftDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.closeDrawers();
            }
        });
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                binding.drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                binding.leftDrawer.removeAllViews();
                binding.leftDrawer.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && binding.leftDrawer.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        binding.drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_login:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //  navigation drawer에 클릭 리스너를 선택
    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition) {
        View view = binding.contentFrame;
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
        binding.contentOverlay.setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        return screenShotable;
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        switch (slideMenuItem.getName()) {
            case MainActivity.CLOSE:
                return screenShotable;

            case MainActivity.MAINFRAGMENT:
                MainFragment fragment = MainFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                    return replaceFragment(fragment, position);

            case MainActivity.FAVORITES:
                FavoritesFragment fragment1 = FavoritesFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment1).commit();
                    return replaceFragment(fragment1, position);
            default:
                return screenShotable;
        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        binding.drawerLayout.closeDrawers();
    }

    public void addViewToContainer(View view) {
        binding.leftDrawer.addView(view);
    }
}
