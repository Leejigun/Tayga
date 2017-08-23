package com.dopy.dopy.tayga.model.game;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.RefreshContainer;

import java.util.List;

/**
 * Created by Dopy on 2017-08-13.
 */

public class GameRcvAdapter extends RecyclerView.Adapter<GameViewHolder> {
    Context context;
    List<GameItem> list;

    public GameRcvAdapter(Context context, List<GameItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GameViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gameitem_cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        ((GameViewHolder)holder).bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

