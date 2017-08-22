package com.dopy.dopy.tayga.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentFavoritesBinding;
import com.dopy.dopy.tayga.model.ContainerRefresh;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.favorites.FavortiesStreamerViewPagerAdapter;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.game.GameRcvAdapter;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {
    FragmentFavoritesBinding binding;
    List<BroadcastModel> models;
    ContainerRefresh containerRefresh;
    User user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    BroadcastRcvAdapter adapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentFavoritesBinding.bind(view);
        containerRefresh = new ContainerRefresh(binding.rotateFavoritesloading, binding.refreshLayoutFavorites,binding.containerRotate);
        models = new ArrayList<>();
        setUpFirebase();
        refreshFavoritesList();
    }

    private void setUpFirebase() {
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        user = myApplication.getUser();
    }

    private void setUpViewPager(List<BroadcastModel> streamerNameList){
        if(streamerNameList.size()==0){
            binding.indicator.setVisibility(View.GONE);
            binding.favoritFragmentViewPager.setVisibility(View.GONE);
        }else {
            FavortiesStreamerViewPagerAdapter viewPagerAdapter = new FavortiesStreamerViewPagerAdapter(streamerNameList, getActivity().getApplication());
            binding.favoritFragmentViewPager.setAdapter(viewPagerAdapter);
            binding.indicator.setViewPager(binding.favoritFragmentViewPager);
            viewPagerAdapter.registerDataSetObserver(binding.indicator.getDataSetObserver());
            containerRefresh.stopLoading();
        }
    }
    private void setUpRecyclerView(List<GameItem>gameNameList){
        GameRcvAdapter rcvAdapter = new GameRcvAdapter(getActivity().getApplication(),gameNameList);
        binding.favoriteFragmentGameRcv.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.favoriteFragmentGameRcv.setAdapter(rcvAdapter);
    }

    private void refreshFavoritesList() {
        Log.d("FavoritesFragment","call refreshFavoritesList");

        databaseReference.child("Favorites").child("Streamer").child(user.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<BroadcastModel> streamer= new ArrayList<BroadcastModel>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("FavoritesFragment","Streamer=>"+((TwitchStream)ds.getValue(TwitchStream.class)).showTitle());
                    streamer.add(ds.getValue(TwitchStream.class));
                }
                setUpViewPager(streamer);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("Favorites").child("Game").child(user.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<GameItem>gameNameList=new ArrayList<GameItem>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("FavoritesFragment","Game ==>"+((GameItem)ds.getValue(GameItem.class)).showTitle());
                    gameNameList.add(ds.getValue(GameItem.class));
                }
                setUpRecyclerView(gameNameList);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
