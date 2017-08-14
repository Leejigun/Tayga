package com.dopy.dopy.tayga.model.recommanded;

import android.view.View;

import com.dopy.dopy.tayga.databinding.HeaderBinding;
import com.dopy.dopy.tayga.model.BaseRcvViewHolder;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.imbryk.viewPager.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Dopy on 2017-08-14.
 */

public class RecommanededPagerViewHolder extends BaseRcvViewHolder {
    List<BroadcastModel> list;
    HeaderBinding binding;

    public RecommanededPagerViewHolder(View itemView) {
        super(itemView);
        binding=HeaderBinding.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        list=((RecommandedStreamList)data).getList();
        LoopViewPager viewpager = binding.viewpager;
        CircleIndicator indicator = binding.indicator;
        RecommandedPagerAdapter adapter =new RecommandedPagerAdapter(list);
        viewpager.setAdapter(adapter);
        indicator.setViewPager(viewpager);
    }
}
