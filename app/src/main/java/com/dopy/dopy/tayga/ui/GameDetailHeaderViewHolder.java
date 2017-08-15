package com.dopy.dopy.tayga.ui;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.databinding.GameDetailHeaderBinding;
import com.dopy.dopy.tayga.model.BaseRcvViewHolder;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;

/**
 * Created by Dopy on 2017-08-15.
 */

public class GameDetailHeaderViewHolder extends BaseRcvViewHolder {
    GameDetailHeaderBinding binding;
    public GameDetailHeaderViewHolder(View itemView) {
        super(itemView);
        binding = GameDetailHeaderBinding.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        String type=data.getClass().toString();
        if(TwitchStream.class.toString().equals(type)){
            TwitchStream twitchStream = (TwitchStream)data;
            Log.d("GameDetailHeader",twitchStream.showTitle());
            Log.d("GameDetailHeader",twitchStream.preview.medium.toString());
            binding.setModel((BroadcastModel) data);
            Glide.with(itemView.getContext()).load(((TwitchStream)data).preview.medium).into(binding.imvStreamIamge);
            Glide.with(itemView.getContext()).load(((TwitchStream)data).channel.logo).into(binding.imvGameDetailLogo);
        }
    }
}
