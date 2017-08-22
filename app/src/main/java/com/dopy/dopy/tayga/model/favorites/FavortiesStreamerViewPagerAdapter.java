package com.dopy.dopy.tayga.model.favorites;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ViewpagerCardviewBinding;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.dopy.dopy.tayga.ui.GameDetailPageActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by Dopy on 2017-08-22.
 */

public class FavortiesStreamerViewPagerAdapter extends PagerAdapter {
    ViewpagerCardviewBinding binding;
    List<TwitchStream> streamerList;
    Context context;

    public FavortiesStreamerViewPagerAdapter(List<TwitchStream> streamerList, Context context) {
        this.streamerList = streamerList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return streamerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
            final TwitchStream stream = streamerList.get(position);
            final View view = LayoutInflater.from(context).inflate(R.layout.viewpager_cardview, container, false);
            binding = ViewpagerCardviewBinding.bind(view);
            binding.setStreamerModel(stream);

            Glide.with(context).load(stream.channel.logo)
                    .placeholder(R.drawable.ic_tayga)
                    .crossFade()
                    .into(binding.imvViewPager);
            binding.containerViewPager.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TwitchStream twitchStream = streamerList.get(position);
                    Intent intent = new Intent(v.getRootView().getContext(),GameDetailPageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("GameDetailPageActivity",Parcels.wrap(stream));
                    v.getContext().startActivity(intent);
                }
            });

            container.addView(view);
            return view;
    }
}
