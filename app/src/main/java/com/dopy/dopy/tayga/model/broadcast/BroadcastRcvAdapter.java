package com.dopy.dopy.tayga.model.broadcast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.BR;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.BroadcastCardviewBinding;
import com.dopy.dopy.tayga.model.twich.TwitchViewHolder;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.List;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastRcvAdapter extends ParallaxRecyclerAdapter<BroadcastModel> {

    Context context;
    public BroadcastRcvAdapter(List<BroadcastModel> data, final Context context) {
        super(data);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<BroadcastModel> Adapter, int i) {
        switch (Adapter.getData().get(i).getClass().toString()){
            case "TwitchStream":
                return new TwitchViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.twich_stream_cardview,viewGroup,false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<BroadcastModel> Adapter, int i) {
        BroadcastModel data = Adapter.getData().get(i);
        switch (data.getClass().toString()){
            case "TwitchStream":
                ((TwitchViewHolder)viewHolder).bind(data);
        }
    }

    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<BroadcastModel> Adapter) {
        return Adapter.getData().size();
    }

    @Override
    public void setData(List<BroadcastModel> data) {
        super.setData(data);
    }
}
