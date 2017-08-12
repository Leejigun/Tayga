package com.dopy.dopy.tayga.model;

import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.BroadcastCardviewBinding;

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
        Log.d("BroadcastViewHolder","bind getTag()"+model.getTag());
            switch (model.getTag()) {
            case BroadcastModel.HEARTHSTONE:
                Glide.with(binding.getRoot()).load(R.drawable.gamesnapshot).into(binding.bcSanpshot);
                break;
            case BroadcastModel.BATTLE_GROUND:
                Glide.with(binding.getRoot()).load(R.drawable.gamenapshot2).into(binding.bcSanpshot);
                break;
            case BroadcastModel.OVERWATCH:
                Glide.with(binding.getRoot()).load(R.drawable.gamenapshot3).into(binding.bcSanpshot);
                break;
        }

    }
}
