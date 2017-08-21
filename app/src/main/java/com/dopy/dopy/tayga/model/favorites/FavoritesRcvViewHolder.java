package com.dopy.dopy.tayga.model.favorites;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FavoritesCardviewBinding;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Dopy on 2017-08-21.
 */

public class FavoritesRcvViewHolder extends BaseRcvViewHolder {
    FavoritesCardviewBinding binding;
    Context context;
    User user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Boolean isFavorites = true;

    public FavoritesRcvViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        binding = FavoritesCardviewBinding.bind(itemView);
        MyApplication myApplication = (MyApplication) context;
        user = myApplication.getUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void bind(Object data) {
        if (data.getClass().toString().equals(GameItem.class.toString())) {
            GameItem gameItem = (GameItem) data;
            binding.setFavoritesModle(gameItem);
            Glide.with(context)
                    .load(gameItem.game.box.medium)
                    .placeholder(R.drawable.ic_tayga_logo)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .crossFade()
                    .into(binding.favoritesBanner);
        } else if (data.getClass().toString().equals(TwitchStream.class.toString())) {
            TwitchStream twitchStream = (TwitchStream) data;
            binding.setFavoritesModle((TwitchStream) data);
            Glide.with(context)
                    .load(twitchStream.channel.logo)
                    .placeholder(R.drawable.ic_tayga_logo)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .crossFade()
                    .into(binding.favoritesBanner);
        } else {

        }
    }

    private void setUpClickStreamerListener(final TwitchStream twitchStream) {
        binding.containerFavoritesCardViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorites) {
                    databaseReference.child("Favorites").child("Streamer").child(user.getUserID()).child(twitchStream.channel.name).removeValue();
                    databaseReference.child("FavoritesCount").child()
                    isFavorites = !isFavorites;
                }
            });
        }
    }

    private void setUpClickGameListener() {
        binding.containerFavoritesCardViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorites) {
                    isFavorites = !isFavorites;
                } else {
                    isFavorites = !isFavorites;
                }
            }
        });
    }
}
