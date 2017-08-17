package com.dopy.dopy.tayga.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.ui.ContainerRefresh;

import java.util.List;

/**
 * Created by Dopy on 2017-08-09.
 */

public class FavoritesRcvAdapter extends RecyclerView.Adapter<FavoritesViewHolder>{

    Context context;
    List<FavorityItem> favorityItems;
    ContainerRefresh containerRefresh;

    public FavoritesRcvAdapter(List<FavorityItem> favorityItems,Context context,ContainerRefresh containerRefresh) {
        this.context = context;
        this.favorityItems = favorityItems;
        this.containerRefresh = containerRefresh;
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

    public void setData(List<BroadcastModel> list){
        containerRefresh.stopLoading();
        if (list.size()==0){
            return;
        }

    }
}
