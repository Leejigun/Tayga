package com.dopy.dopy.tayga.mainActivity;


import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentAfricaTvpopularBroadListBinding;
import com.dopy.dopy.tayga.mainActivity.recyclerView.BroadcastRecyclerViewAdapter;
import com.dopy.dopy.tayga.model.BaseBroadcast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AfricaTVPopularBroadListFragment extends Fragment {
    List<BaseBroadcast> broadcastList;
    FragmentAfricaTvpopularBroadListBinding binding;

    public AfricaTVPopularBroadListFragment() {
        // Required empty public constructor
    }
    public static AfricaTVPopularBroadListFragment newInstance() {
        AfricaTVPopularBroadListFragment fragment = new AfricaTVPopularBroadListFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_africa_tvpopular_broad_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding= FragmentAfricaTvpopularBroadListBinding.bind(getView());
        broadcastList=new ArrayList<BaseBroadcast>();
        inputTestData();
        drowRecyclerView();
    }
    public void drowRecyclerView(){
        binding.rvAfricaTVPopular.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvAfricaTVPopular.setAdapter(new BroadcastRecyclerViewAdapter(broadcastList));
    }

    public void inputTestData(){
        BaseBroadcast testData=new BaseBroadcast();
        testData.setTitle("아프리카 방송");
        broadcastList.add(testData);
    }
}
