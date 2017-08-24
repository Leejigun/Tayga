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
import com.dopy.dopy.tayga.databinding.FragmentStreamListBinding;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.RefreshContainer;
import com.dopy.dopy.tayga.model.RefreshDoneInterface;
import com.dopy.dopy.tayga.model.TwitchListContainer;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.game.GameItemList;
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
public class StreamListFragment extends Fragment {
    final String TAG="StreamListFragment";
    FragmentStreamListBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RefreshContainer refreshContainer;
    public StreamListFragment() {
        // Required empty public constructor
    }
    public static StreamListFragment newInstance(){
        return new StreamListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stream_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentStreamListBinding.bind(view);
        refreshContainer =new RefreshContainer(binding.rotateStreamloading,binding.PullStreamFragment,binding.frameLayoutStreamloading);
        binding.PullStreamFragment.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshDataSet();
            }
        });
        setUpFirebase();
        refreshDataSet();
    }

    private void setUpFirebase(){
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    private void refreshDataSet(){
        refreshContainer.startLoading();
        final List<BroadcastModel> modelList=new ArrayList<>();
        setUpGameList(modelList);
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
                broadcastModels.add(1,streamerList);
                refreshDoneInterface.refreshDone();
                //끝나면 즐겨찾기 게임 목록 받는 함수 호출
                Log.d(TAG,"twitchStreams 즐겨찾기에 "+twitchStreams.size()+"개의 데이터가 들어옴");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void setUpGameList(final List<BroadcastModel> broadcastModels){
        SearchTwitch searchTwitch = new SearchTwitch();
        searchTwitch.getGameList(0,3,broadcastModels, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                ((GameItemList)broadcastModels.get(0)).setTag("이 게임은 어떠신가요? (Top 3)");
                getFavoritesStreamerList(broadcastModels, new RefreshDoneInterface() {
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
        String tag = "인기방송 Top 20";
        searchTwitch.getTwitch(0, 20, broadcastModels,tag, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpRecyclerView(broadcastModels);
            }
        });
    }
    private void setUpRecyclerView(List<BroadcastModel> broadcastModels){
        Log.d(TAG,"setUpRecyclerView broadcastModels.size()=>"+broadcastModels.size());
        BroadcastRcvAdapter adapter = new BroadcastRcvAdapter(broadcastModels,getActivity().getApplication());
        binding.rcvStreamFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rcvStreamFragment.setAdapter(adapter);
        refreshContainer.stopLoading();
    }
}
