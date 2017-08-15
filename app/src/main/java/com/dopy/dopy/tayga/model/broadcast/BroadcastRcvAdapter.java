package com.dopy.dopy.tayga.model.broadcast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.game.GameHeaderViewHolder;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.game.GameViewHolder;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.dopy.dopy.tayga.model.twitch.TwitchViewHolder;

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

    public BroadcastRcvAdapter(List<BroadcastModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    public void setData(List<BroadcastModel> list) {
        if(this.models.size()>0){
            List<BroadcastModel> newlist=new ArrayList<>();
            newlist.add(models.get(0));
            newlist.addAll(list);
            this.models=newlist;
        }else {
            models = list;
        }
        notifyDataSetChanged();
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
