package com.dopy.dopy.tayga.model.broadcast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.game.GameHeaderViewHolder;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.dopy.dopy.tayga.model.twitch.TwitchViewHolder;
import com.dopy.dopy.tayga.model.ContainerRefresh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastRcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final int RECOMMANDED_HEADER = 101;
    final int TWITCH = 102;
    final int GAME_HEADER = 103;

    Context context;
    List<BroadcastModel> models;
    ContainerRefresh containerRefresh;
    public BroadcastRcvAdapter(List<BroadcastModel> models, Context context, ContainerRefresh containerRefresh) {
        this.models = models;
        this.context = context;
        this.containerRefresh=containerRefresh;
    }

    public void setData(List<BroadcastModel> list) {
        containerRefresh.stopLoading();
       if(list.size()==0){
           Log.d("BroadcastRcvAdapter","list.size()==0");
           return;
       }else if(models.size()==0){
           this.models=list;

       }else if(getItemViewType(0)==GAME_HEADER){
           Log.d("BroadcastRcvAdapter","getItemViewType(0)==GAME_HEADER");
           List<BroadcastModel> list1 = new ArrayList<>();
           list1.add(models.get(0));
           list1.addAll(list);
           this.models=list1;
       }else{
           Log.d("BroadcastRcvAdapter","else");
           this.models=list;
       }
        notifyDataSetChanged();
    }
    public void restoreData(List<BroadcastModel> list){
        this.models=list;
        containerRefresh.stopLoading();
        notifyDataSetChanged();
    }


    public List<BroadcastModel>getData(){
        return models;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case RECOMMANDED_HEADER:
                return null;
            case TWITCH:
                return new TwitchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.twitch_stream_cardview, parent, false));
            case GAME_HEADER:
                return new GameHeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.game_logo_header,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case RECOMMANDED_HEADER:
                break;
            case TWITCH:
                ((TwitchViewHolder) holder).bind(models.get(position));
                break;
            case GAME_HEADER:
                ((GameHeaderViewHolder)holder).bind(models.get(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        BroadcastModel model = models.get(position);
        String type = model.getClass().toString();
        if (TwitchStream.class.toString().equals(type)) {
            return TWITCH;
        } else if(GameItem.class.toString().equals(type)){
            return GAME_HEADER;
        }else{
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
