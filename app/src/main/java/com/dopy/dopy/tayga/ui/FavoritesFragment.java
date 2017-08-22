package com.dopy.dopy.tayga.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentFavoritesBinding;
import com.dopy.dopy.tayga.model.RefreshContainer;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.RefreshDoneInterface;
import com.dopy.dopy.tayga.model.TwitchListContainer;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.favorites.FavortiesStreamerViewPagerAdapter;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.game.GameRcvAdapter;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.google.firebase.database.ChildEventListener;
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
    private static final String TAG = "FavoritesFragment";
    FragmentFavoritesBinding binding;
    RefreshContainer refreshContainer;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int streamerCount= 0;
    int GameCount = 0;

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
        refreshContainer = new RefreshContainer(binding.rotateFavoritesloading, binding.refreshLayoutFavorites,binding.containerRotate);
        refreshContainer.pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFavoritesList();
            }
        });
        setUpFirebase();
        refreshFavoritesList();
    }

    private void setUpFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshFavoritesList();
    }

    private void refreshFavoritesList() {
        Log.d(TAG,"call refreshFavoritesList");
        refreshContainer.startLoading();
        final List<BroadcastModel> models=new ArrayList<>();
        getFavoritesStreamerList(models, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                getFavoritesGameList(models, new RefreshDoneInterface() {
                    @Override
                    public void refreshDone() {
                        models.add(0,new BroadcastModel());
                        setUpRecyclerView(models);
                    }
                });
            }
        });

    }
    private void getFavoritesStreamerList(final List<BroadcastModel> broadcastModels, final RefreshDoneInterface refreshDoneInterface){
        User user = ((MyApplication)getActivity().getApplication()).getUser();
        final TwitchListContainer streamerList = new TwitchListContainer();
        databaseReference.child("Favorites").child("Streamer").child(user.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<TwitchStream>twitchStreams =new ArrayList<TwitchStream>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Log.d(TAG,ds.getValue(TwitchStream.class).showTitle());
                    twitchStreams.add(ds.getValue(TwitchStream.class));
                }
                streamerList.setListType(TwitchListContainer.STREAMER);
                streamerList.setTwitchStreamList(twitchStreams);
                broadcastModels.add(streamerList);
                refreshDoneInterface.refreshDone();
                //끝나면 즐겨찾기 게임 목록 받는 함수 호출
                Log.d(TAG,"twitchStreams 즐겨찾기에 "+twitchStreams.size()+"개의 데이터가 들어옴");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getFavoritesGameList(final List<BroadcastModel> broadcastModels, final RefreshDoneInterface refreshDoneInterface){
        User user=((MyApplication)getActivity().getApplication()).getUser();
        final TwitchListContainer gameList= new TwitchListContainer();
        databaseReference.child("Favorites").child("Game").child(user.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<GameItem>gameItems = new ArrayList<GameItem>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Log.d(TAG,ds.getValue(GameItem.class).showTitle());
                    gameItems.add(ds.getValue(GameItem.class));
                }
                gameList.setGameItemList(gameItems);
                gameList.setListType(TwitchListContainer.GAME);
                gameList.setTag("당신이 즐겨찾기한 게임");
                broadcastModels.add(1,gameList);
                refreshDoneInterface.refreshDone();
                Log.d(TAG,"gameItems 즐겨찾기에 "+gameItems.size()+"개의 데이터가 들어옴");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void setUpRecyclerView(List<BroadcastModel>gameNameList){
        BroadcastRcvAdapter adapter =new BroadcastRcvAdapter(gameNameList,getActivity().getApplication());
        binding.favoriteFragmentRcv.setAdapter(adapter);
        binding.favoriteFragmentRcv.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshContainer.stopLoading();
    }
}
