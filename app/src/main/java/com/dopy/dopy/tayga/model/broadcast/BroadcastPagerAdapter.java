package com.dopy.dopy.tayga.model.broadcast;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ItemOfBroadcastInViewpagerBinding;
import com.dopy.dopy.tayga.model.twich.TwitchStream;

import java.util.List;

/**
 * Created by Dopy on 2017-08-09.
 */

public class BroadcastPagerAdapter extends PagerAdapter {
    List<BroadcastModel> models;
    ItemOfBroadcastInViewpagerBinding recommandedBinding;
    public BroadcastPagerAdapter(List<BroadcastModel> models) {
        models.add(0,new BroadcastModel());
    }
    public List<BroadcastModel> getData(){
        return models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_of_broadcast_in_viewpager,container,false);
        recommandedBinding = ItemOfBroadcastInViewpagerBinding.bind(view);
        switch (models.get(position).getClass().toString()){
            case "TwitchStream":
                TwitchStream twitchStream = (TwitchStream) models.get(position);
                recommandedBinding.setTwitchStream(twitchStream);
                Glide.with(container.getContext()).load(twitchStream.preview.medium).into(recommandedBinding.vpImage);
                break;
            case "BroadcastModel":
                recommandedBinding.setTwitchStream(null);
                Glide.with(container.getContext()).load(R.drawable.main_background).into(recommandedBinding.vpImage);
                break;
            default:
                break;
        }

        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View)object);
    }
}
