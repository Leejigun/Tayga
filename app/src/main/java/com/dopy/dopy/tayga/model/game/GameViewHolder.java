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
        switch (gameItem.getTag()){
            case GameItem.OVERWATCH:
                Glide.with(binding.getRoot()).load("https://static-cdn.jtvnw.net/ttv-boxart/Overwatch-272x380.jpg").into(binding.imvGameItem);
                break;
            case GameItem.HEARTHSTONE:
                Glide.with(binding.getRoot()).load("https://static-cdn.jtvnw.net/ttv-boxart/Hearthstone-272x380.jpg").into(binding.imvGameItem);
                break;
            case GameItem.BATTLE_GROUND:
                Glide.with(binding.getRoot()).load("https://static-cdn.jtvnw.net/ttv-boxart/PLAYERUNKNOWN%27S%20BATTLEGROUNDS-272x380.jpg").into(binding.imvGameItem);
                break;
            case GameItem.LOL:
                Glide.with(binding.getRoot()).load("https://static-cdn.jtvnw.net/ttv-boxart/League%20of%20Legends-272x380.jpg").into(binding.imvGameItem);
                break;
            case GameItem.HOS:
                Glide.with(binding.getRoot()).load("https://static-cdn.jtvnw.net/ttv-boxart/Heroes%20of%20the%20Storm-272x380.jpg").into(binding.imvGameItem);
                break;
            case GameItem.DOTA2:
                Glide.with(binding.getRoot()).load("https://static-cdn.jtvnw.net/ttv-boxart/Dota%202-272x380.jpg").into(binding.imvGameItem);
                break;
            case GameItem.MINECRAFT:
                Glide.with(binding.getRoot()).load("https://static-cdn.jtvnw.net/ttv-boxart/Minecraft-272x380.jpg").into(binding.imvGameItem);
                break;
            case GameItem.WOW:
                Glide.with(binding.getRoot()).load("https://static-cdn.jtvnw.net/ttv-boxart/World%20of%20Warcraft-272x380.jpg").into(binding.imvGameItem);
                break;
        }
    }
}
