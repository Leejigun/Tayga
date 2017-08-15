package com.dopy.dopy.tayga.model.game;

import android.view.View;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.databinding.GameitemCardviewBinding;
import com.dopy.dopy.tayga.model.BaseRcvViewHolder;

/**
 * Created by Dopy on 2017-08-13.
 */

public class GameViewHolder extends BaseRcvViewHolder {
    GameitemCardviewBinding binding;
    public GameViewHolder(View itemView) {
        super(itemView);
        binding= GameitemCardviewBinding.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        GameItem gameItem = (GameItem) data;
        binding.setGameItem(gameItem);
        Glide.with(binding.getRoot()).load(gameItem.game.box.medium).into(binding.imvGameItem);
    }
}
