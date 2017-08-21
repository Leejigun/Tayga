package com.dopy.dopy.tayga.model.game;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.GameLogoHeaderBinding;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Dopy on 2017-08-16.
 */

public class GameHeaderViewHolder extends BaseRcvViewHolder {
    GameLogoHeaderBinding binding;
    Context context;
    Boolean isFavorites=false;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    User user;
    public GameHeaderViewHolder(View itemView,Context context) {
        super(itemView);
        this.context=context;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        binding = GameLogoHeaderBinding.bind(itemView);
        MyApplication application=(MyApplication)context;
        user=application.getUser();
    }

    @Override
    public void bind(Object data) {
        GameItem gameItem = (GameItem)data;
        binding.setGameItem(gameItem);
        setUpFavoritesMark(gameItem);
        setUpClickListener(gameItem);
        Glide.with(itemView.getContext())
                .load(gameItem.game.box.medium)
                .placeholder(R.drawable.placeholder_broadcast)
                .error(R.drawable.ic_error_outline_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .crossFade()
                .into(binding.imvGameLogo);
    }
    private void setUpFavoritesMark(final GameItem gameItem){
        databaseReference.child("Favorites").child("Game").child(user.getUserID()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals(gameItem.showGameName())){
                    binding.favortiesGame.setImageResource(R.drawable.ic_favorite_black_24dp);
                    isFavorites=true;
                    return;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUpClickListener(final GameItem gameItem){
        binding.containerFavortiesGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFavorites){
                    databaseReference.child("Favorites").child("Game").child(user.getUserID()).child(gameItem.showGameName()).removeValue();
                    databaseReference.child("FavoritesCount").child("Game").child(gameItem.showGameName()).child(user.getUserID()).removeValue();
                    isFavorites=!isFavorites;
                    binding.favortiesGame.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    databaseReference.child("Favorites").child("Game").child(user.getUserID()).child(gameItem.showGameName()).setValue(gameItem);
                    databaseReference.child("FavoritesCount").child("Game").child(gameItem.showGameName()).child(user.getUserID()).setValue(gameItem);
                    isFavorites=!isFavorites;
                    binding.favortiesGame.setImageResource(R.drawable.ic_favorite_black_24dp);
                }
            }
        });
    }
}
