package com.dopy.dopy.tayga.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FavoritesCardviewBinding;

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
