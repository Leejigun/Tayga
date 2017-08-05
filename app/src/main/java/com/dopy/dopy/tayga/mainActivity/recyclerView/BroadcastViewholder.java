package com.dopy.dopy.tayga.mainActivity.recyclerView;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dopy.dopy.tayga.databinding.BroadcastCardviewBinding;

/**
 * Created by Dopy on 2017-08-05.
 *방송 리스트들을 연결해줄 뷰 홀더
 * broadcast_cardview.xml을 연결
 */

public class BroadcastViewholder extends RecyclerView.ViewHolder {

    BroadcastCardviewBinding broadcastCardviewBinding;

    public BroadcastViewholder(View itemView) {
        super(itemView);
        broadcastCardviewBinding = DataBindingUtil.bind(itemView);
    }
}
