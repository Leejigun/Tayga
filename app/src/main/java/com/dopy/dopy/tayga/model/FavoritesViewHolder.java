package com.dopy.dopy.tayga.model;

import android.view.View;
import android.widget.ImageView;

import com.dopy.dopy.tayga.R;

/**
 * Created by Dopy on 2017-08-09.
 */

public class FavoritesViewHolder extends BaseRcvViewHolder {

    ImageView deleteButton;
    public FavoritesViewHolder(View itemView) {
        super(itemView);
        deleteButton=itemView.findViewById(R.id.fcvDeleteFavorites);
    }

    @Override
    public void bind(Object data) {
    }
}
