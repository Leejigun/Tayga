package com.dopy.dopy.tayga.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.List;

/**
 * Created by Dopy on 2017-08-09.
 */

public class FavoritesRcvAdapter extends ParallaxRecyclerAdapter<FavorityItem>{
    Context context;

    public FavoritesRcvAdapter(List<FavorityItem> data, final Context context) {
        super(data);
        this.context=context;
    }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, final ParallaxRecyclerAdapter<FavorityItem> Adapter, final int i) {
        ((FavoritesViewHolder) viewHolder).bind(Adapter.getData().get(i));
        switch ((i)%3){
            case 0:
                Glide.with(context).load(R.drawable.gamesnapshot).into(((FavoritesViewHolder) viewHolder).imageCategory);
                ((FavoritesViewHolder) viewHolder).textTitle.setText("하스스톤");
                break;
            case 1:
                Glide.with(context).load(R.drawable.gamenapshot2).into(((FavoritesViewHolder) viewHolder).imageCategory);
                ((FavoritesViewHolder) viewHolder).textTitle.setText("배틀 그라운드");
                break;
            case 2:
                Glide.with(context).load(R.drawable.gamenapshot3).into(((FavoritesViewHolder) viewHolder).imageCategory);
                ((FavoritesViewHolder) viewHolder).textTitle.setText("오버워치");
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<FavorityItem> parallaxRecyclerAdapter, int i) {
        return new FavoritesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorites_cardview,viewGroup,false));    }

    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<FavorityItem> Adapter) {
        return Adapter.getData().size();
    }
}
