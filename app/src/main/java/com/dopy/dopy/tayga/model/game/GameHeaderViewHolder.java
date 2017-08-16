package com.dopy.dopy.tayga.model.game;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.GameLogoHeaderBinding;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;

/**
 * Created by Dopy on 2017-08-16.
 */

public class GameHeaderViewHolder extends BaseRcvViewHolder {
    GameLogoHeaderBinding binding;
    public GameHeaderViewHolder(View itemView) {
        super(itemView);
        binding = GameLogoHeaderBinding.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        GameItem gameItem = (GameItem)data;
        binding.setGameItem(gameItem);

        Glide.with(itemView.getContext())
                .load(gameItem.game.box.medium)
                .placeholder(R.drawable.placeholder_broadcast)
                .error(R.drawable.ic_error_outline_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .crossFade()
                .into(binding.imvGameLogo);
    }
}
