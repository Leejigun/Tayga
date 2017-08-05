package com.dopy.dopy.tayga.mainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by Dopy on 2017-08-04.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    private int tabcount;
    public TabPagerAdapter(FragmentManager fm,int tabcount) {
        super(fm);
        this.tabcount=tabcount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return TwitchPopularBroadListFragment.newInstance();
            case 1:
                return TwitchPopularBroadListFragment.newInstance();
            case 2:
                return TwitchPopularBroadListFragment.newInstance();
            default:
                Log.d("TabPagerAdapter","getItem position out");
                return TwitchPopularBroadListFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
