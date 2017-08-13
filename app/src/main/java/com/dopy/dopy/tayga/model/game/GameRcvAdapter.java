package com.dopy.dopy.tayga.model.game;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.List;

/**
 * Created by Dopy on 2017-08-13.
 */

public class GameRcvAdapter extends ParallaxRecyclerAdapter<GameItem> {
    Context context;

    public GameRcvAdapter(List<GameItem> data, Context context) {
        super(data);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<GameItem> adapter, int i) {
        return new GameViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gameitem_cardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<GameItem> adapter, int i) {
        ((GameViewHolder)viewHolder).bind(adapter.getData().get(i));

    }


    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<GameItem> adapter) {
        return adapter.getData().size();
    }
}
