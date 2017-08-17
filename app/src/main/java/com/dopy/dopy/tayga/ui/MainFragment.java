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
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.twitch.SearchTwitch;
import com.victor.loading.rotate.RotateLoading;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import static org.parceler.guava.collect.ComparisonChain.start;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    FragmentMainBinding binding;
    List<BroadcastModel> broadcastList;
    BroadcastRcvAdapter adapter;
    ContainerRefresh containerRefresh;

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
        containerRefresh=new ContainerRefresh(binding.rotateloading,binding.containerMainFragment);
        setUpRecyclerView();

        if(savedInstanceState!=null){
            Log.d("MainFragment","savedInstanceState!=null");
            adapter.restoreData((List<BroadcastModel>) Parcels.unwrap(savedInstanceState.getParcelable("BroadcastModelList")));
        }else {
            refreshBroadcastList(0);
        }
    }

    public void setUpRecyclerView(){
        containerRefresh.pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshBroadcastList(0);
            }
        });
        broadcastList=new ArrayList<>();
        adapter= new BroadcastRcvAdapter(broadcastList,getContext(),containerRefresh);
        binding.rcvMainFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvMainFragment.setAdapter(adapter);
    }

    //    새로고침
    private void refreshBroadcastList(int pageOffSet) {
        SearchTwitch searchTwitch =new SearchTwitch();
        searchTwitch.getTwitch(pageOffSet,adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("BroadcastModelList",Parcels.wrap(adapter.getData()));
    }
}
