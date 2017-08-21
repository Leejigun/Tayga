package com.dopy.dopy.tayga.model.favorites;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.ContainerRefresh;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;

import java.util.List;

/**
 * Created by Dopy on 2017-08-21.
 */

public class FavoritesRcvAdapter extends RecyclerView.Adapter<BaseRcvViewHolder> {
    Context context;
    List<BroadcastModel> models;
    ContainerRefresh containerRefresh;

    public FavoritesRcvAdapter(Context context, List<BroadcastModel> models, ContainerRefresh containerRefresh) {
        this.context = context;
        this.models = models;
        this.containerRefresh = containerRefresh;
    }

    @Override
    public BaseRcvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoritesRcvViewHolder(LayoutInflater.from(context).inflate(R.layout.favorites_cardview,parent,false),context);
    }

    @Override
    public void onBindViewHolder(BaseRcvViewHolder holder, int position) {
        ((FavoritesRcvViewHolder)holder).bind(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
