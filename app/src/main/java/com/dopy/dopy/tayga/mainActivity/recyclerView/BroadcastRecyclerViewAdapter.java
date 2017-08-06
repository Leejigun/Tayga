package com.dopy.dopy.tayga.mainActivity.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.BaseBroadcast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Dopy on 2017-08-05.
 */

public class BroadcastRecyclerViewAdapter extends RecyclerView.Adapter<BroadcastViewholder> {
    List<BaseBroadcast> broadcastList = new ArrayList<>();
    public void add(BaseBroadcast data){
        broadcastList.add(data);
        notifyDataSetChanged();
    }

    public BroadcastRecyclerViewAdapter(List<BaseBroadcast> broadcastList) {
        this.broadcastList = broadcastList;
    }

    @Override
    public BroadcastViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BroadcastViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.broadcast_cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(BroadcastViewholder holder, int position) {
        BaseBroadcast baseBroadcast = broadcastList.get(position);
        holder.broadcastCardviewBinding.cvTitle.setText(baseBroadcast.getTitle());
    }

    @Override
    public int getItemCount() {
        return broadcastList.size();
    }
}
