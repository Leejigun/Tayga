package com.dopy.dopy.tayga.model.broadcast;

import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.BroadcastCardviewBinding;
import com.dopy.dopy.tayga.model.BaseRcvViewHolder;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastViewHolder extends BaseRcvViewHolder {

    BroadcastCardviewBinding binding;

    public BroadcastViewHolder(View itemView) {
        super(itemView);
        binding = BroadcastCardviewBinding.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        BroadcastModel model = (BroadcastModel) data;
    }
}
