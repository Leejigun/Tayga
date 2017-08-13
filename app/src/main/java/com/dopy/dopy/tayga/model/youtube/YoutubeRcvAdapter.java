package com.dopy.dopy.tayga.model.youtube;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.YoutubeCardviewBinding;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.List;

/**
 * Created by Dopy on 2017-08-13.
 */

public class YoutubeRcvAdapter extends ParallaxRecyclerAdapter<SearchData> {

    Context context;
    public YoutubeRcvAdapter(List<SearchData> data , Context context) {
        super(data);
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<SearchData> Adapter, int i) {
        return new YoutubeViewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.youtube_cardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<SearchData> Adapter, int i) {
        SearchData searchData = Adapter.getData().get(i);
        Log.d(this.getClass().toString(),"돌고있는 데이터는? position :"+i+" searcData :"+searchData.snippet.title);
        ((YoutubeViewholder)viewHolder).bind(searchData);
    }

    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<SearchData> Adapter) {
        return Adapter.getData().size();
    }
}
