package com.dopy.dopy.tayga.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentMainBinding;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.twitch.SearchTwitch;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    FragmentMainBinding binding;
    List<BroadcastModel> broadcastList;
    BroadcastRcvAdapter adapter;

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
        broadcastList=new ArrayList<>();
        adapter= new BroadcastRcvAdapter(broadcastList,getContext());
        refreshBroadcastList(0);
        binding.rcvMainFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvMainFragment.setAdapter(adapter);
    }

    //    새로고침
    private void refreshBroadcastList(int pageOffSet) {
        SearchTwitch searchTwitch =new SearchTwitch();
        searchTwitch.getTwitch(pageOffSet,adapter);
    }



}
