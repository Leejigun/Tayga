package com.dopy.dopy.tayga.model.youtube;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.BaseRcvViewHolder;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.dopy.dopy.tayga.ui.GameDetailHeaderViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dopy on 2017-08-13.
 */

public class YoutubeRcvAdapter extends RecyclerView.Adapter<BaseRcvViewHolder> {
    final int STREAMINFO =101;
    final int YOUTUBE =102;

    List<BroadcastModel> list;

    public YoutubeRcvAdapter(List<BroadcastModel> list) {
        this.list = list;
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
    public void onBindViewHolder(BaseRcvViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case STREAMINFO:
                ((GameDetailHeaderViewHolder)holder).bind(list.get(position));
                break;
            case YOUTUBE:
                ((YoutubeViewholder)holder).bind(list.get(position));
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
        BroadcastModel header = list.get(0);
        list = new ArrayList<>();
        list.add(header);
        list.addAll(model);
        notifyDataSetChanged();
    }
}

