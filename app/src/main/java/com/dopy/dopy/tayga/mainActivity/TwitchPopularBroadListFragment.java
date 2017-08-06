package com.dopy.dopy.tayga.mainActivity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentTwitchPopularBroadListBinding;
import com.dopy.dopy.tayga.mainActivity.recyclerView.BroadcastRecyclerViewAdapter;
import com.dopy.dopy.tayga.model.BaseBroadcast;

import java.util.ArrayList;
import java.util.List;

public class TwitchPopularBroadListFragment extends Fragment {
    List<BaseBroadcast> broadcastList;

    FragmentTwitchPopularBroadListBinding binding;

    public TwitchPopularBroadListFragment() {
        // Required empty public constructor
    }

    public static TwitchPopularBroadListFragment newInstance() {
        TwitchPopularBroadListFragment fragment = new TwitchPopularBroadListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twitch_popular_broad_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentTwitchPopularBroadListBinding.bind(getView());
        broadcastList=new ArrayList<>();
        inputTestData();
        drowRecyclerView();
    }

//    트위치 방송 리스트 그려주기
    public void drowRecyclerView(){
        binding.rvTwitchPopular.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvTwitchPopular.setAdapter(new BroadcastRecyclerViewAdapter(broadcastList));
    }

    public void inputTestData(){
        BaseBroadcast testData=new BaseBroadcast();
        testData.setTitle("트위치 방송");
        broadcastList.add(testData);
    }
}
