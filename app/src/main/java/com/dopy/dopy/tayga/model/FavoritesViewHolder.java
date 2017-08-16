package com.dopy.dopy.tayga.model;

import android.view.View;

import com.dopy.dopy.tayga.databinding.FavoritesCardviewBinding;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;

/**
 * Created by Dopy on 2017-08-09.
 */

public class FavoritesViewHolder extends BaseRcvViewHolder {
   FavoritesCardviewBinding binding;
    public FavoritesViewHolder(View itemView) {
        super(itemView);
        binding=FavoritesCardviewBinding.bind(itemView);

    }

    @Override
    public void bind(Object data) {
    }
}
