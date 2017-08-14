package com.dopy.dopy.tayga.model.youtube;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.twich.TwitchViewHolder;

import java.util.List;

/**
 * Created by Dopy on 2017-08-13.
 */

public class YoutubeRcvAdapter extends RecyclerView.Adapter<TwitchViewHolder> {

    Context context;
    List<SearchData> list;

    public YoutubeRcvAdapter(Context context, List<SearchData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public TwitchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TwitchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(TwitchViewHolder holder, int position) {
        ((TwitchViewHolder)holder).bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
