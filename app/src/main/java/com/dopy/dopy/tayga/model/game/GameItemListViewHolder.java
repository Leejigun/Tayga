package com.dopy.dopy.tayga.model.game;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.dopy.dopy.tayga.databinding.RecyclerviewWapLayoutBinding;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;

/**
 * Created by Dopy on 2017-08-24.
 */

public class GameItemListViewHolder extends BaseRcvViewHolder{
    RecyclerviewWapLayoutBinding binding;
    public GameItemListViewHolder(View itemView) {
        super(itemView);
        binding = RecyclerviewWapLayoutBinding.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        GameItemList gameItemList = (GameItemList)data;
        binding.recyclerview.setLayoutManager(new GridLayoutManager(itemView.getContext(),2));
        GameRcvAdapter adapter = new GameRcvAdapter(itemView.getContext(),gameItemList.getList());
        binding.recyclerview.setAdapter(adapter);
        if(gameItemList.getTag()!=null){
            binding.txtRecyclerviewTag.setText(gameItemList.getTag());
            binding.txtRecyclerviewTag.setVisibility(View.VISIBLE);
        }
    }
}
