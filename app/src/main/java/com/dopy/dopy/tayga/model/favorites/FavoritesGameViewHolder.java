package com.dopy.dopy.tayga.model.favorites;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dopy.dopy.tayga.databinding.RecyclerviewWapLayoutBinding;
import com.dopy.dopy.tayga.model.TwitchListContainer;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.game.GameRcvAdapter;

import java.util.List;

/**
 * Created by Dopy on 2017-08-22.
 */

public class FavoritesGameViewHolder extends BaseRcvViewHolder {
    RecyclerviewWapLayoutBinding binding;
    public FavoritesGameViewHolder(View itemView) {
        super(itemView);
        binding = RecyclerviewWapLayoutBinding.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        List<GameItem> gameItemList = ((TwitchListContainer)data).getGameItemList();
        GameRcvAdapter adapter =new GameRcvAdapter(itemView.getContext(),gameItemList);
        binding.txtRecyclerviewTag.setText(((TwitchListContainer)data).getTag());
        binding.txtRecyclerviewTag.setVisibility(View.VISIBLE);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.recyclerview.setAdapter(adapter);

    }
}
