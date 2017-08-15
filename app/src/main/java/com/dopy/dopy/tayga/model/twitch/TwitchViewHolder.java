package com.dopy.dopy.tayga.model.twitch;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.databinding.TwitchStreamCardviewBinding;
import com.dopy.dopy.tayga.model.BaseRcvViewHolder;
import com.dopy.dopy.tayga.ui.GameDetailPageActivity;

import org.parceler.Parcels;

/**
 * Created by Dopy on 2017-08-14.
 */

public class TwitchViewHolder extends BaseRcvViewHolder {
    TwitchStreamCardviewBinding binding;

    public TwitchViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        final TwitchStream twitchStream =(TwitchStream)data;
        binding.setTwitchModel(twitchStream);
        Glide.with(itemView.getContext()).load(twitchStream.channel.logo).into(binding.tscvLogo);
        Glide.with(itemView.getContext()).load(twitchStream.preview.medium).into(binding.tscvPreView);

        binding.containerTwitchCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TwitchStream stream = twitchStream;
                view.getRootView().getContext().startActivity(new Intent(itemView.getContext(),GameDetailPageActivity.class).putExtra("GameDetailPageActivity",Parcels.wrap(stream)));
            }
        });
    }
}
