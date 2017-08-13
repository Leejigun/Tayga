package com.dopy.dopy.tayga.model.broadcast;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ItemOfBroadcastInViewpagerBinding;

import java.util.List;

/**
 * Created by Dopy on 2017-08-09.
 */

public class BroadcastPagerAdapter extends PagerAdapter {
    List<BroadcastModel> models;
    ItemOfBroadcastInViewpagerBinding recommandedBinding;
    public BroadcastPagerAdapter(List<BroadcastModel> models) {

        this.models = models;
        models.add(0,new BroadcastModel("","",0,null,"",0));
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
        recommandedBinding.setBroadcastModel(models.get(position));
        if(position==0){
            Glide.with(view.getContext()).load(R.drawable.main_background).into(recommandedBinding.vpImage);
        }else{
            switch ((position)%3){
                case 0:
                    Glide.with(view.getContext()).load(R.drawable.gamesnapshot).into(recommandedBinding.vpImage);
                    break;
                case 1:
                    Glide.with(view.getContext()).load(R.drawable.gamenapshot2).into(recommandedBinding.vpImage);
                    break;
                case 2:
                    Glide.with(view.getContext()).load(R.drawable.gamenapshot3).into(recommandedBinding.vpImage);
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
