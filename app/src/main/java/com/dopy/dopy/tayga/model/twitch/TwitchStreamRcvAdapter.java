package com.dopy.dopy.tayga.model.twitch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;

import java.util.List;

/**
 * Created by Dopy on 2017-08-25.
 */

public class TwitchStreamRcvAdapter extends RecyclerView.Adapter<TwitchViewHolder> {
    List<TwitchStream> streamList;
    Context context;

    public TwitchStreamRcvAdapter(Context context,List<TwitchStream> streamList) {
        this.streamList = streamList;
        this.context = context;
    }

    @Override
    public TwitchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TwitchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.twitch_stream_cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(TwitchViewHolder holder, int position) {
        ((TwitchViewHolder)holder).bind(streamList.get(position));
    }

    @Override
    public int getItemCount() {
        return streamList.size();
    }
}
