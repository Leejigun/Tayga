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
import com.dopy.dopy.tayga.databinding.FragmentGameBinding;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.RefreshContainer;
import com.dopy.dopy.tayga.model.RefreshDoneInterface;
import com.dopy.dopy.tayga.model.TwitchListContainer;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.game.GameRcvAdapter;
import com.dopy.dopy.tayga.model.twitch.SearchTwitch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment{
    final String TAG = "GameFragment";
    FragmentGameBinding binding;
    BroadcastRcvAdapter adapter;
    RefreshContainer refreshContainer;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public GameFragment() {
        // Required empty public constructor
    }
    public static GameFragment newInstance(){
        return new GameFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentGameBinding.bind(view);
        refreshContainer =new RefreshContainer(binding.rotateGameloading,binding.containerGameFragment,binding.containerrotateGameloading);
        refreshContainer.pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshGameItemList();
            }
        });
        setUpFireBase();
        refreshGameItemList();
    }
    private void setUpFireBase(){
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    public void setUpRecyclerView(List<BroadcastModel> models){
        refreshContainer.pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshGameItemList();
            }
        });
        adapter = new BroadcastRcvAdapter(models,getActivity().getApplication());
        binding.rcvGameFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvGameFragment.setAdapter(adapter);
        refreshContainer.stopLoading();
    }

    public void refreshGameItemList(){
        refreshContainer.startLoading();
        final List<BroadcastModel> models = new ArrayList<>();
        getFavoritesGameList(models, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setGameFirst9Items(models);
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
                broadcastModels.clear();
                broadcastModels.add(gameList);
                refreshDoneInterface.refreshDone();
                Log.d(TAG,"gameItems 즐겨찾기에 "+gameItems.size()+"개의 데이터가 들어옴");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void setGameFirst9Items(final List<BroadcastModel> broadcastModels){
        SearchTwitch searchTwitch = new SearchTwitch();
        String tag = "Top 1 ~ top 9";
        searchTwitch.getGameList(0, 9, broadcastModels,tag,1, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpPopularSteam(broadcastModels);
            }
        });
    }
    private void setUpPopularSteam(final List<BroadcastModel> broadcastModels){
        SearchTwitch searchTwitch =new SearchTwitch();
        String tag = "실시간 최고 시청자 방송";
        searchTwitch.getTwitch(0, 1, broadcastModels,tag,2, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setGameListLast11Items(broadcastModels);
            }
        });
    }

    private void setGameListLast11Items(final List<BroadcastModel> broadcastModels){
        SearchTwitch searchTwitch = new SearchTwitch();
        String tag = "Top 10 ~ Top 20";
        searchTwitch.getGameList(9, 11, broadcastModels,tag,3, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpRecyclerView(broadcastModels);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
