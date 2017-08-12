package com.dopy.dopy.tayga.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.BR;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.BroadcastCardviewBinding;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.List;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastRcvAdapter extends ParallaxRecyclerAdapter<BroadcastModel> {

    Context context;
    BroadcastCardviewBinding binding;

    public BroadcastRcvAdapter(List<BroadcastModel> data, final Context context) {
        super(data);
        Log.d("BroadcastRcvAdapter", "crate BroadcastRcvAdapter data.size() :" + data.size());
        Log.d("BroadcastRcvAdapter", "crate BroadcastRcvAdapter data.get(0).getTitle :" + data.get(0).getTitle());
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<BroadcastModel> Adapter, int i) {
        Log.d("BroadcastRcvAdapter","onCreateViewHolderImpl position->"+i);
        return new BroadcastViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.broadcast_cardview, viewGroup, false));
    }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<BroadcastModel> Adapter, int i) {
        BroadcastModel data = Adapter.getData().get(i);
        ((BroadcastViewHolder)viewHolder).bind(data);
        ((BroadcastViewHolder)viewHolder).binding.setVariable(BR.modelItme,data);
    }

    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<BroadcastModel> Adapter) {
        return Adapter.getData().size();
    }
}
