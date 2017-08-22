package com.dopy.dopy.tayga.model.favorites;

import android.util.Log;
import android.view.View;

import com.dopy.dopy.tayga.databinding.ViewpagerStreamerLayoutBinding;
import com.dopy.dopy.tayga.model.TwitchListContainer;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;

import java.util.List;

/**
 * Created by Dopy on 2017-08-22.
 */

public class FavoritesViewPagerViewHolder extends BaseRcvViewHolder {
    final String TAG = "FavoritesViewPager";
    ViewpagerStreamerLayoutBinding binding;
    FavortiesStreamerViewPagerAdapter adapter;
    public FavoritesViewPagerViewHolder(View itemView) {
        super(itemView);
        binding = ViewpagerStreamerLayoutBinding.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        List<TwitchStream> list = ((TwitchListContainer)data).getTwitchStreamList();
        Log.d(TAG,"call bind list.size()=>"+list.size());
        adapter=new FavortiesStreamerViewPagerAdapter(list,itemView.getContext());
        binding.favoritViewPager.setAdapter(adapter);
        binding.favoritViewPagerIndicator.setViewPager(binding.favoritViewPager);
    }
}
