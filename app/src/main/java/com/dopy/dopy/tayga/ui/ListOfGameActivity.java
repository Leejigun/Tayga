package com.dopy.dopy.tayga.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.baoyz.widget.PullRefreshLayout;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityListOfGameBinding;
import com.dopy.dopy.tayga.model.ContainerRefresh;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.twitch.SearchTwitch;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ListOfGameActivity extends AppCompatActivity {

    ActivityListOfGameBinding binding;
    BroadcastRcvAdapter adapter;
    GameItem gameItem;
    ContainerRefresh containerRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_of_game);
        containerRefresh=new ContainerRefresh(binding.loadingGameOList,binding.listOfGmaeRefreshLayout,binding.containerloadingGameOList);
        gameItem = Parcels.unwrap(getIntent().getParcelableExtra("ListOfGameActivity"));
        getSupportActionBar().setTitle(gameItem.showTitle());
        setUpRecyclerView();

        if(savedInstanceState!=null){
            adapter.restoreData((List<BroadcastModel>) Parcels.unwrap(savedInstanceState.getParcelable("BroadcastModelList")));
        }else{
            refreshBroadcastListOfGame(0);
        }
    }

    private void setUpRecyclerView() {
        List<BroadcastModel> list = new ArrayList<>();
        list.add(gameItem);
        binding.listOfGmaeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshBroadcastListOfGame(0);
            }
        });
        adapter = new BroadcastRcvAdapter(list,getApplication(),containerRefresh);
        binding.rcvBroadListOfGame.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvBroadListOfGame.setAdapter(adapter);
    }

    public void refreshBroadcastListOfGame(int offset) {
        SearchTwitch searchTwitch =new SearchTwitch();
        searchTwitch.getListOfGame(offset,gameItem.game.name,adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("BroadcastModelList",Parcels.wrap(adapter.getData()));
    }
}
