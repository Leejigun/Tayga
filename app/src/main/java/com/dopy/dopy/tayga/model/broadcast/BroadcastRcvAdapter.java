package com.dopy.dopy.tayga.model.broadcast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.recommanded.RecommandedStreamList;
import com.dopy.dopy.tayga.model.recommanded.RecommanededPagerViewHolder;
import com.dopy.dopy.tayga.model.twich.TwitchStream;
import com.dopy.dopy.tayga.model.twich.TwitchViewHolder;

import java.util.List;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastRcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final int RECOMMANDED_HEADER = 101;
    final int TWITCH = 102;

    Context context;
    List<BroadcastModel> models;

    public BroadcastRcvAdapter(Context context,List<BroadcastModel> models) {
        this.models=models;
        this.context = context;
    }
    public void setData(List<BroadcastModel> list){
        models=list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case RECOMMANDED_HEADER:
                return new RecommanededPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false));
            case TWITCH:
                return new TwitchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.twich_stream_cardview,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType=getItemViewType(position);
        switch (viewType){
            case RECOMMANDED_HEADER:
                ((RecommanededPagerViewHolder)holder).bind(models.get(position));
                break;
            case TWITCH:
                ((TwitchViewHolder)holder).bind(models.get(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        BroadcastModel model = models.get(position);
        String type = model.getClass().toString();
        if(TwitchStream.class.toString().equals(type)){
            return TWITCH;
        }else if(RecommandedStreamList.class.toString().equals(type)){
            return RECOMMANDED_HEADER;
        }
        else return 0;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
