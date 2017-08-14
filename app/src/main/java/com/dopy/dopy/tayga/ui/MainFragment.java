package com.dopy.dopy.tayga.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentMainBinding;
import com.dopy.dopy.tayga.databinding.HeaderBinding;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.recommanded.RecommandedPagerAdapter;
import com.dopy.dopy.tayga.model.twich.SearchTwitch;
import com.imbryk.viewPager.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    FragmentMainBinding binding;
    HeaderBinding viewPagerBinding;
    List<BroadcastModel> broadcastModelList;
    List<BroadcastModel> RecommendedBroadcastList;

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
        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
        BroadcastRcvAdapter

    }

    //    새로고침
    private void refreshBroadcastModelList(BroadcastRcvAdapter adapter,int pageoffset) {
        Log.d(this.getClass().toString(),"refreshBroadcastModelList");
        SearchTwitch searchTwitch =new SearchTwitch();
        searchTwitch.getTwitch(pageoffset,adapter);
    }
    private void refreshRecommandedModelList(RecommandedPagerAdapter adapter, int pageoffset) {
        Log.d(this.getClass().toString(),"refreshRecommandedModelList");
        SearchTwitch searchTwitch =new SearchTwitch();
        searchTwitch.getRecommandList(pageoffset,adapter);
    }

    private void setUpParallaxRecyclerView() {
        Log.d(this.getClass().toString(),"setUpParallaxRecyclerView");
        broadcastModelList = new ArrayList<>();
        BroadcastRcvAdapter adapter = new BroadcastRcvAdapter(getContext(),broadcastModelList);
        binding.rcvMainFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvMainFragment.setAdapter(adapter);
        refreshBroadcastModelList(adapter,0);
    }

    //  리사이클러 뷰 헤더에 추가될 뷰 페이저를 만든다.
    private View cearteCircleIndicator(RecyclerView recyclerView) {
        Log.d(this.getClass().toString(),"cearteCircleIndicator");
        RecommendedBroadcastList= new ArrayList<>();
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header, recyclerView, false);
        viewPagerBinding = HeaderBinding.bind(header);
        LoopViewPager viewpager = viewPagerBinding.viewpager;
        CircleIndicator indicator = viewPagerBinding.indicator;
        RecommandedPagerAdapter adapter =new RecommandedPagerAdapter(RecommendedBroadcastList);
        viewpager.setAdapter(adapter);
        indicator.setViewPager(viewpager);
        //refreshRecommandedModelList(adapter,0);
        return header;
    }



}
