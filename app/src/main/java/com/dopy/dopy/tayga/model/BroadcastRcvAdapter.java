package com.dopy.dopy.tayga.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastRcvAdapter extends ParallaxRecyclerAdapter<BroadcastModel> {

    Context context;

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
        ((BroadcastViewHolder) viewHolder).bind(data);
        Log.d("BroadcastRcvAdapter", "onBindViewHolderImpl: title->" + data.getTitle() + " position->" + i);

        switch ((i) % 3) {
            case 0:
                Glide.with(context).load(R.drawable.gamesnapshot).into(((BroadcastViewHolder) viewHolder).imageView);
                ((BroadcastViewHolder) viewHolder).textTitle.setText(getData().get(i).getTitle());
                break;
            case 1:
                Glide.with(context).load(R.drawable.gamenapshot2).into(((BroadcastViewHolder) viewHolder).imageView);
                ((BroadcastViewHolder) viewHolder).textTitle.setText(getData().get(i).getTitle());
                break;
            case 2:
                Glide.with(context).load(R.drawable.gamenapshot3).into(((BroadcastViewHolder) viewHolder).imageView);
                ((BroadcastViewHolder) viewHolder).textTitle.setText(getData().get(i).getTitle());
                break;
        }
    }

    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<BroadcastModel> Adapter) {
        return Adapter.getData().size();
    }
}
