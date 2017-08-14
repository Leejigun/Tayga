package com.dopy.dopy.tayga.model.twich;

import android.view.View;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.databinding.TwichStreamCardviewBinding;
import com.dopy.dopy.tayga.model.BaseRcvViewHolder;

/**
 * Created by Dopy on 2017-08-14.
 */

public class TwitchViewHolder extends BaseRcvViewHolder {
    TwichStreamCardviewBinding binding;

    public TwitchViewHolder(View itemView) {
        super(itemView);
        binding = TwichStreamCardviewBinding.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        TwitchStream twitchStream =(TwitchStream)data;
        binding.setTwitchModel(twitchStream);
        Glide.with(itemView.getContext()).load(twitchStream.preview.medium).into(binding.tscvPreView);
    }
}
