package com.dopy.dopy.tayga.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;

import java.util.List;

/**
 * Created by Dopy on 2017-08-09.
 */

public class FavoritesRcvAdapter extends RecyclerView.Adapter<FavoritesViewHolder>{

    Context context;
    List<FavorityItem> favorityItems;

    public FavoritesRcvAdapter(List<FavorityItem> favorityItems,Context context) {
        this.context = context;
        this.favorityItems = favorityItems;
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoritesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder holder, int position) {
        ((FavoritesViewHolder) holder).bind(favorityItems.get(position));
    }

    @Override
    public int getItemCount() {
        return favorityItems.size();
    }
}
