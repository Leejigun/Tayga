package com.dopy.dopy.tayga.model.twitch;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dopy.dopy.tayga.databinding.RecyclerviewWapLayoutBinding;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;

/**
 * Created by Dopy on 2017-08-25.
 */

public class TwitchGameListViewHolder extends BaseRcvViewHolder {
    RecyclerviewWapLayoutBinding binding;
    public TwitchGameListViewHolder(View itemView) {
        super(itemView);
        binding = RecyclerviewWapLayoutBinding.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        TwitchStreamList twitchStreamList = (TwitchStreamList)data;
        if(twitchStreamList.getTag()!=null){
            binding.txtRecyclerviewTag.setText(twitchStreamList.getTag());
            binding.txtRecyclerviewTag.setVisibility(View.VISIBLE);
        }else{
            binding.txtRecyclerviewTag.setVisibility(View.GONE);
        }
        TwitchStreamRcvAdapter adapter =new TwitchStreamRcvAdapter(itemView.getContext(),twitchStreamList.getList());
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

    }
}
