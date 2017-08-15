package com.dopy.dopy.tayga.model.game;

import android.view.View;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.databinding.GameLogoHeaderBinding;
import com.dopy.dopy.tayga.model.BaseRcvViewHolder;

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
        Glide.with(itemView.getContext()).load(gameItem.game.box.medium).into(binding.imvGameLogo);
    }
}
