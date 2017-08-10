package com.dopy.dopy.tayga.model;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.MainActivity;
import com.dopy.dopy.tayga.R;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastViewHolder extends BaseRcvViewHolder {
    ImageView imageView;
    public BroadcastViewHolder(View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.bcSanpshot);
    }

    @Override
    public void bind(Object data) {
        Glide.with(itemView.getContext()).load(R.drawable.gamesnapshot).into(imageView);
    }
}
