package com.dopy.dopy.tayga.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentGameBinding;
import com.dopy.dopy.tayga.model.ContainerRefresh;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.game.GameRcvAdapter;
import com.dopy.dopy.tayga.model.twitch.SearchTwitch;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment{
    FragmentGameBinding binding;
    GameRcvAdapter adapter;
    ContainerRefresh containerRefresh;
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
        containerRefresh=new ContainerRefresh(binding.rotateGameloading,binding.containerGameFragment,binding.containerrotateGameloading);
        setUpRecyclerView();

        if(savedInstanceState!=null){
            adapter.restroeData((List<GameItem>) Parcels.unwrap(savedInstanceState.getParcelable("GameItemList")));
        }else{
            refreshGameItemList();
        }
    }
    public void setUpRecyclerView(){
        List<GameItem> gameItemList =new ArrayList<>();
        containerRefresh.pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshGameItemList();
            }
        });
        adapter = new GameRcvAdapter(getActivity().getApplication(),gameItemList,containerRefresh);
        binding.rcvGameFragment.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.rcvGameFragment.setAdapter(adapter);
    }

    public void refreshGameItemList(){
        SearchTwitch searchTwitch =new  SearchTwitch();
        searchTwitch.getGameList(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("GameItemList",Parcels.wrap(adapter.getData()));
    }
}
