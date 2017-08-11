package com.dopy.dopy.tayga.model;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;

import java.util.List;

/**
 * Created by Dopy on 2017-08-09.
 */

public class BroadcastPagerAdapter extends PagerAdapter {
    List<BroadcastModel> models;
    ImageView imageView;
    TextView title;
    boolean isHeadAdd=false;
    public BroadcastPagerAdapter(List<BroadcastModel> models) {
        this.models = models;
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
        imageView = view.findViewById(R.id.vpImage);
        title = view.findViewById(R.id.vpTitle);
        if(!isHeadAdd){
            Glide.with(view.getContext()).load(R.drawable.main_background).into(imageView);
            title.setText("");
            isHeadAdd=!isHeadAdd;
        }else{
            switch ((position)%3){
                case 0:
                    Glide.with(view.getContext()).load(R.drawable.gamesnapshot).into(imageView);
                    break;
                case 1:
                    Glide.with(view.getContext()).load(R.drawable.gamenapshot2).into(imageView);
                    break;
                case 2:
                    Glide.with(view.getContext()).load(R.drawable.gamenapshot3).into(imageView);
                    break;
            }
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
