package com.dopy.dopy.tayga.model.favorites;

import android.app.Application;
import android.view.View;

import com.dopy.dopy.tayga.databinding.FavoritesCountBoxBinding;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.RefreshDoneInterface;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Dopy on 2017-08-23.
 */

public class FavoritesCountViewHolder extends BaseRcvViewHolder {
    FavoritesCountBoxBinding binding;
    MyApplication myApplication;
    int streamerCount=0;
    int gameCount=0;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public FavoritesCountViewHolder(View itemView, Application application) {
        super(itemView);
        binding=FavoritesCountBoxBinding.bind(itemView);
        this.myApplication=(MyApplication)application;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    @Override
    public void bind(Object data) {
        final User user=myApplication.getUser();
        getSteamerFavoritesCount(user, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                getGameFavoritesCount(user, new RefreshDoneInterface() {
                    @Override
                    public void refreshDone() {
                        setUpText();
                    }
                });
            }
        });

    }

    private void getSteamerFavoritesCount(User user, final RefreshDoneInterface refreshDoneInterface){
        databaseReference.child("Favorites").child("Streamer").child(user.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                streamerCount =(int) dataSnapshot.getChildrenCount();
                refreshDoneInterface.refreshDone();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void getGameFavoritesCount(User user, final RefreshDoneInterface refreshDoneInterface){
        databaseReference.child("Favorites").child("Game").child(user.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gameCount=(int)dataSnapshot.getChildrenCount();
                refreshDoneInterface.refreshDone();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUpText(){
        binding.txtStreamerCount.setText(Integer.toString(streamerCount)+"개의 방송과..");
        binding.txtGameCount.setText(Integer.toString(gameCount)+"개의 게임을..");
    }
}
