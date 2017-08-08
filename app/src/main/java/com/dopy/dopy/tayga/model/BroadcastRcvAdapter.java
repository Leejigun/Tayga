package com.dopy.dopy.tayga.model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastRcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<BroadcastModel> broadcastModels;
    static final int TYPE_HEADER=101;
    static final int TYPE_BODY=102;

    public BroadcastRcvAdapter(List<BroadcastModel> broadcastModels) {
        BroadcastModel header=new BroadcastModel(101);
        this.broadcastModels = broadcastModels;
        this.broadcastModels.add(0,header);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Adapter",Integer.toString(viewType));
        switch (viewType){
            case TYPE_HEADER:
                return new BroadcastViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.broadcast_cardview,parent,false));
            case TYPE_BODY:
                return new BroadcastViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.broadcast_cardview,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewtype = getItemViewType(position);
        switch (viewtype){
            case TYPE_HEADER:
                ((BroadcastViewHolder) holder).bind(broadcastModels.get(position));
                break;
            case TYPE_BODY:
                ((BroadcastViewHolder) holder).bind(broadcastModels.get(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return broadcastModels.get(position).getViewtype();
    }

    @Override
    public int getItemCount() {
        return broadcastModels.size();
    }
}
