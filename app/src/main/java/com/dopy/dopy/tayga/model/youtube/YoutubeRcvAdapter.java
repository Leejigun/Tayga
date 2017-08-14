package com.dopy.dopy.tayga.model.youtube;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.twich.TwitchViewHolder;
import com.dopy.dopy.tayga.ui.GameDetailPageActivity;

import java.util.List;

/**
 * Created by Dopy on 2017-08-13.
 */

public class YoutubeRcvAdapter extends RecyclerView.Adapter<YoutubeViewholder> {
    List<SearchData> list;
    YouTubeClickInterface youTubeClickInterface;

    public YoutubeRcvAdapter(List<SearchData> list,YouTubeClickInterface youTubeClickInterface) {
        this.list = list;
        this.youTubeClickInterface=youTubeClickInterface;
    }

    @Override
    public YoutubeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new YoutubeViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(YoutubeViewholder holder, int position) {
        ((YoutubeViewholder)holder).bind(list.get(position));
        ((YoutubeViewholder)holder).binding.containeryoutubeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubeClickInterface.itemClick(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
