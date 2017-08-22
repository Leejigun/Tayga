package com.dopy.dopy.tayga.model.twitch;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.GameDetailHeaderBinding;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Dopy on 2017-08-15.
 */

public class GameDetailHeaderViewHolder extends BaseRcvViewHolder {
    GameDetailHeaderBinding binding;
    Context context;
    User currentUser;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    Boolean isLiked = false;
    Boolean isFavofirtes = false;
    MyApplication myApplication;

    public GameDetailHeaderViewHolder(View itemView, Context context) {
        super(itemView);
        binding = GameDetailHeaderBinding.bind(itemView);
        this.context = context;
        myApplication = (MyApplication) context;
        currentUser = myApplication.getUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    @Override
    public void bind(final Object data) {
        String type = data.getClass().toString();
        if (TwitchStream.class.toString().equals(type)) {
            final TwitchStream twitchStream = (TwitchStream) data;
            binding.setModel(twitchStream);
            Glide.with(itemView.getContext())
                    .load(twitchStream.channel.logo)
                    .placeholder(R.drawable.placeholder_broadcast)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .crossFade()
                    .into(binding.imvGameDetailLogo);
            Glide.with(itemView.getContext())
                    .load(twitchStream.preview.medium)
                    .placeholder(R.drawable.placeholder_broadcast)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .crossFade()
                    .into(binding.imvStreamIamge);

//            좋아요 버튼과 즐겨찾기 버튼을 초기화
            setUpLikeandStar(twitchStream);
            setUpClickListener(twitchStream);

        } else {//not a twitch app

        }
    }

    private void setUpClickListener(final TwitchStream twitchStream) {
        binding.btnPrayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPackageInstalled("tv.twitch.android.app", itemView.getContext())) {
                    //installed twitch app
                    String url = ("twitch://stream/" + (twitchStream.channel.name));
                    Log.d("GameDetailHeader", "url -> " + url);
                    Intent intent = view.getContext().getPackageManager().getLaunchIntentForPackage("tv.twitch.android.app");
                    intent.setData(Uri.parse(url));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);
                } else {
                    String url = "market://details?id=" + "tv.twitch.android.app";
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    itemView.getContext().startActivity(i);
                }
            }
        });
        binding.containerStarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked) {
                    databaseReference.child("Like").child("Streamer").child(currentUser.getUserID()).child(twitchStream.channel.name).removeValue();
                    binding.iconStar.setImageResource(R.drawable.ic_star_border_black_24dp);
                    databaseReference.child("LikeCount").child("Streamer").child(twitchStream.channel.name).child(currentUser.getUserID()).removeValue();
                    isLiked=!isLiked;
                } else {
                    databaseReference.child("Like").child("Streamer").child(currentUser.getUserID()).child(twitchStream.channel.name).setValue(twitchStream);
                    binding.iconStar.setImageResource(R.drawable.ic_star_black_24dp);
                    databaseReference.child("LikeCount").child("Streamer").child(twitchStream.channel.name).child(currentUser.getUserID()).setValue(twitchStream);
                    isLiked=!isLiked;

                }
            }
        });
        binding.containerFavortiesIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFavofirtes){
                    databaseReference.child("Favorites").child("Streamer").child(currentUser.getUserID()).child(twitchStream.channel.name).removeValue();
                    binding.iconFavorites.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    databaseReference.child("FavoritesCount").child("Streamer").child(twitchStream.channel.name).child(currentUser.getUserID()).removeValue();
                    isFavofirtes=!isFavofirtes;
                }else{
                    databaseReference.child("Favorites").child("Streamer").child(currentUser.getUserID()).child(twitchStream.channel.name).setValue(twitchStream);
                    binding.iconFavorites.setImageResource(R.drawable.ic_favorite_black_24dp);
                    databaseReference.child("FavoritesCount").child("Streamer").child(twitchStream.channel.name).child(currentUser.getUserID()).setValue(twitchStream);
                    isFavofirtes=!isFavofirtes;
                }
            }
        });
    }

    private void setUpLikeandStar(final TwitchStream twitchStream) {
        currentUser= myApplication.getUser();
        databaseReference.child("Like").child("Streamer").child(currentUser.getUserID()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals(twitchStream.channel.name)) {
                    binding.iconStar.setImageResource(R.drawable.ic_star_black_24dp);
                    isLiked = true;
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

        databaseReference.child("Favorites").child("Streamer").child(currentUser.getUserID()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals(twitchStream.channel.name)) {
                    binding.iconFavorites.setImageResource(R.drawable.ic_favorite_black_24dp);
                    isFavofirtes = true;
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

    private boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
