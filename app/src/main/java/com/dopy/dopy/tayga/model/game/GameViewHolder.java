package com.dopy.dopy.tayga.model.game;

import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.databinding.GameitemCardviewBinding;
import com.dopy.dopy.tayga.model.BaseRcvViewHolder;
import com.dopy.dopy.tayga.ui.GameDetailPageActivity;
import com.dopy.dopy.tayga.ui.ListOfGameActivity;

import org.parceler.Parcels;

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
        final GameItem gameItem = (GameItem) data;
        binding.setGameItem(gameItem);
        binding.containerGameItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getRootView().getContext().startActivity(new Intent(itemView.getContext(),ListOfGameActivity.class).putExtra("ListOfGameActivity", Parcels.wrap(gameItem)));
            }
        });
        Glide.with(binding.getRoot()).load(gameItem.game.box.medium).into(binding.imvGameItem);
    }
}
