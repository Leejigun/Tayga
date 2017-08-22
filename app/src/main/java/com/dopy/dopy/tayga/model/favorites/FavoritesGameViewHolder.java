package com.dopy.dopy.tayga.model.favorites;

import android.support.v7.widget.GridLayoutManager;
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
        binding.recyclerview.setLayoutManager(new GridLayoutManager(itemView.getContext(),2));
        binding.recyclerview.setAdapter(adapter);
    }
}
