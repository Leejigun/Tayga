package com.dopy.dopy.tayga.model.youtube;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.dopy.dopy.tayga.model.twitch.GameDetailHeaderViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dopy on 2017-08-13.
 */

public class YoutubeRcvAdapter extends RecyclerView.Adapter<BaseRcvViewHolder>{
    final int STREAMINFO =101;
    final int YOUTUBE =102;
    SetOnclickYoutubePlay onclickYoutubePlay;
    List<BroadcastModel> list;

    public YoutubeRcvAdapter(List<BroadcastModel> list) {
        this.list = list;
    }

    public void addSetOnClickListener(SetOnclickYoutubePlay listener){
        this.onclickYoutubePlay=listener;
    }

    @Override
    public BaseRcvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case STREAMINFO:
                return new GameDetailHeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.game_detail_header,parent,false));
            case YOUTUBE:
                return new YoutubeViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_cardview,parent,false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseRcvViewHolder holder, final int position) {
        switch (getItemViewType(position)){
            case STREAMINFO:
                ((GameDetailHeaderViewHolder)holder).bind(list.get(position));
                break;
            case YOUTUBE:
                ((YoutubeViewholder)holder).bind(list.get(position));
                ((YoutubeViewholder)holder).binding.containeryoutubeCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("YoutubeRcvAdapter", "call onClick");
                        onclickYoutubePlay.onClickYoutube(view, (SearchData) list.get(position));
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        String type =list.get(position).getClass().toString();
        if(SearchData.class.toString().equals(type)){
            return YOUTUBE;
        }else if(TwitchStream.class.toString().equals(type)){
            return STREAMINFO;
        }else{
            Log.d("YoutubeRcvAdapter", "getItemViewType position:"+position + "is not matched");
            throw new NullPointerException();
        }
    }
    public void setData(List<BroadcastModel> model){
        Log.d("YoutubeRcvAdapter","position 0 =>"+model.get(0).showTitle());
        BroadcastModel header = list.get(0);
        list = new ArrayList<>();
        list.add(header);
        list.addAll(model);
        notifyDataSetChanged();
    }

    public void restoreData(List<BroadcastModel> model){
        this.list=model;
        notifyDataSetChanged();
    }

    public List<BroadcastModel> getData(){
        return list;
    }
}

