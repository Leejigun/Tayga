package com.dopy.dopy.tayga.mainActivity;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentYoutubePopularBroadListBinding;
import com.dopy.dopy.tayga.mainActivity.recyclerView.BroadcastRecyclerViewAdapter;
import com.dopy.dopy.tayga.model.BaseBroadcast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class YoutubePopularBroadListFragment extends Fragment {
    List<BaseBroadcast> broadcastList;
    FragmentYoutubePopularBroadListBinding binding;
    public YoutubePopularBroadListFragment() {
        // Required empty public constructor
    }

    public static YoutubePopularBroadListFragment newInstance() {
        YoutubePopularBroadListFragment fragment = new YoutubePopularBroadListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_youtube_popular_broad_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding= FragmentYoutubePopularBroadListBinding.bind(getView());
        broadcastList=new ArrayList<>();
        inputTestData();
        drowRecyclerView();
    }
    public void drowRecyclerView(){
        binding.rcvYoutubePopular.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvYoutubePopular.setAdapter(new BroadcastRecyclerViewAdapter(broadcastList));
    }

    public void inputTestData(){
        BaseBroadcast testData=new BaseBroadcast();
        testData.setTitle("유투브 방송");
        broadcastList.add(testData);
    }
}
