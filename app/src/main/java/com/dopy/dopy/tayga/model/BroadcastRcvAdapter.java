package com.dopy.dopy.tayga.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dopy.dopy.tayga.R;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastRcvAdapter extends ParallaxRecyclerAdapter<BroadcastModel> {

    static final int TYPE_HEADER=101;
    static final int TYPE_BODY=102;
    Context context;

    public BroadcastRcvAdapter(List<BroadcastModel> data, final Context context) {
        super(data);
        this.context=context;
        this.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(context, "You clicked '" + position + "'", Toast.LENGTH_SHORT).show();
            }
        });
    }
   @Override
   public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<BroadcastModel> Adapter, int i) {
       int viewtype=Adapter.getData().get(i).getViewtype();
       switch (viewtype){
           case TYPE_HEADER:
               return new BroadcastViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.broadcast_cardview,viewGroup,false));
           case TYPE_BODY:
               return new BroadcastViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.broadcast_cardview,viewGroup,false));
       }
       return null;
   }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<BroadcastModel> Adapter, int i) {
        int viewtype=Adapter.getData().get(i).getViewtype();
        switch (viewtype){
            case TYPE_HEADER:
                ((BroadcastViewHolder) viewHolder).bind(Adapter.getData().get(i));
                break;
            case TYPE_BODY:
                ((BroadcastViewHolder) viewHolder).bind(Adapter.getData().get(i));
                break;
        }
    }

    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<BroadcastModel> Adapter) {
        return Adapter.getData().size();
    }

}
