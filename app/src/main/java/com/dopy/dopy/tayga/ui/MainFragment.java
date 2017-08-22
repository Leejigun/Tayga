package com.dopy.dopy.tayga.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentMainBinding;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.RefreshContainer;
import com.dopy.dopy.tayga.model.RefreshDoneInterface;
import com.dopy.dopy.tayga.model.TwitchListContainer;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.twitch.SearchTwitch;
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
public class MainFragment extends Fragment {
    final String TAG ="MainFragment";

    FragmentMainBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RefreshContainer refreshContainer;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(this.getClass().toString(),"ononViewCreated");
        binding = FragmentMainBinding.bind(view);
        refreshContainer = new RefreshContainer(binding.rotateloading,binding.pullRefreshMainFragment,binding.rotateloadingFrameLayout);
        binding.pullRefreshMainFragment.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshDataSet();
            }
        });
        setUpFIrebase();
        refreshDataSet();
    }
    private void setUpFIrebase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void refreshDataSet(){
        refreshContainer.startLoading();
        final List<BroadcastModel> broadcastModels= new ArrayList<>();
        getFavoritesStreamerList(broadcastModels, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                getFavoritesGameList(broadcastModels, new RefreshDoneInterface() {
                    @Override
                    public void refreshDone() {
                        setUpTwitchList(broadcastModels);
                    }
                });
            }

        });
    }
    private void setUpTwitchList(final List<BroadcastModel> broadcastModels){
        SearchTwitch searchTwitch = new SearchTwitch();
        searchTwitch.getTwitch(0, 5, broadcastModels, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpRecyclerView(broadcastModels);
            }
        });
    }

    private void setUpRecyclerView(List<BroadcastModel> broadcastModels){
        Log.d(TAG,"setUpRecyclerView broadcastModels.size()=>"+broadcastModels.size());
        BroadcastRcvAdapter adapter = new BroadcastRcvAdapter(broadcastModels,getActivity().getApplication());
        binding.rcvMainFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rcvMainFragment.setAdapter(adapter);
        refreshContainer.stopLoading();
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
                broadcastModels.add(gameList);
                refreshDoneInterface.refreshDone();
                Log.d(TAG,"gameItems 즐겨찾기에 "+gameItems.size()+"개의 데이터가 들어옴");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
