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
    final String TAG = "StreamListFragment";
    FragmentStreamListBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RefreshContainer refreshContainer;

    public StreamListFragment() {
        // Required empty public constructor
    }

    public static StreamListFragment newInstance() {
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
        refreshContainer = new RefreshContainer(binding.rotateStreamloading, binding.PullStreamFragment, binding.frameLayoutStreamloading);
        binding.PullStreamFragment.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshDataSet();
            }
        });
        setUpFirebase();
        refreshDataSet();
    }

    private void setUpFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void refreshDataSet() {
        refreshContainer.startLoading();
        final List<BroadcastModel> modelList = new ArrayList<>();
        getFavoritesStreamerList(modelList, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpTwitchListFirst3Item(modelList);
            }
        });
    }

    private void getFavoritesStreamerList(final List<BroadcastModel> broadcastModels, final RefreshDoneInterface refreshDoneInterface) {
        User user = ((MyApplication) getActivity().getApplication()).getUser();
        final TwitchListContainer streamerList = new TwitchListContainer();
        databaseReference.child("Favorites").child("Streamer").child(user.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<TwitchStream> twitchStreams = new ArrayList<TwitchStream>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d(TAG, ds.getValue(TwitchStream.class).showTitle());
                    twitchStreams.add(ds.getValue(TwitchStream.class));
                }
                streamerList.setListType(TwitchListContainer.STREAMER);
                streamerList.setTwitchStreamList(twitchStreams);
                broadcastModels.clear();
                broadcastModels.add(streamerList);
                refreshDoneInterface.refreshDone();
                //끝나면 즐겨찾기 게임 목록 받는 함수 호출
                Log.d(TAG, "twitchStreams 즐겨찾기에 " + twitchStreams.size() + "개의 데이터가 들어옴");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUpTwitchListFirst3Item(final List<BroadcastModel> broadcastModels) {
        SearchTwitch searchTwitch = new SearchTwitch();
        searchTwitch.getTwitch(0, 3, broadcastModels, null, 0, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpGameList(broadcastModels);
            }
        });
    }

    private void setUpGameList(final List<BroadcastModel> broadcastModels) {
        SearchTwitch searchTwitch = new SearchTwitch();
        String tag = "이 게임은 어떤가요? (시청자 수 Top 3)";
        searchTwitch.getGameList(0, 3, broadcastModels, tag, 1, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpTwitchListSecond7Item(broadcastModels);
            }
        });
    }

    private void setUpTwitchListSecond7Item(final List<BroadcastModel> broadcastModels) {
        SearchTwitch searchTwitch = new SearchTwitch();
        String tag ="Top 4 ~ Top 10";
        searchTwitch.getTwitch(3, 7, broadcastModels, tag, 2, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpTwitchListLast3Item(broadcastModels);
            }
        });
    }

    private void setUpTwitchListLast3Item(final List<BroadcastModel> broadcastModels) {
        SearchTwitch searchTwitch = new SearchTwitch();
        String tag ="Top 11 ~ Top 20";
        searchTwitch.getTwitch(10, 10, broadcastModels, tag, 4, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpRecyclerView(broadcastModels);
            }
        });
    }

    private void setUpRecyclerView(List<BroadcastModel> broadcastModels) {
        Log.d(TAG, "setUpRecyclerView broadcastModels.size()=>" + broadcastModels.size());
        BroadcastRcvAdapter adapter = new BroadcastRcvAdapter(broadcastModels, getActivity().getApplication());
        binding.rcvStreamFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rcvStreamFragment.setAdapter(adapter);
        refreshContainer.stopLoading();
    }
}
