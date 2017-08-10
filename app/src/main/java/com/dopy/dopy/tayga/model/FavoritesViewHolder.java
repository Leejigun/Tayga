package com.dopy.dopy.tayga.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;

/**
 * Created by Dopy on 2017-08-09.
 */

public class FavoritesViewHolder extends BaseRcvViewHolder {
    ImageView imageCategory;
    ImageView deleteButton;
    TextView textTitle;
    public FavoritesViewHolder(View itemView) {
        super(itemView);
        deleteButton=itemView.findViewById(R.id.fcvDeleteFavorites);
        imageCategory=itemView.findViewById(R.id.fcvCategory);
        textTitle = itemView.findViewById(R.id.fcvTitleFavorites);
    }

    @Override
    public void bind(Object data) {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(itemView.getContext(),"Clicked delete Button",Toast.LENGTH_LONG).show();
            }
        });
    }
}
