package com.dopy.dopy.tayga.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.baoyz.widget.PullRefreshLayout;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityListOfGameBinding;
import com.dopy.dopy.tayga.model.RefreshContainer;
import com.dopy.dopy.tayga.model.RefreshDoneInterface;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.twitch.SearchTwitch;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.offset;


public class ListOfGameActivity extends AppCompatActivity {

    private static final String TAG = "ListOfGameActivity";
    ActivityListOfGameBinding binding;
    BroadcastRcvAdapter adapter;
    GameItem gameItem;
    RefreshContainer refreshContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_of_game);
        Log.d(TAG,"onCreate");
        refreshContainer =new RefreshContainer(binding.loadingGameOList,binding.listOfGmaeRefreshLayout,binding.containerloadingGameOList);
        refreshContainer.pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshBroadcastListOfGame();
            }
        });
        gameItem = Parcels.unwrap(getIntent().getParcelableExtra("ListOfGameActivity"));
        getSupportActionBar().setTitle(gameItem.showTitle());
        refreshBroadcastListOfGame();
    }

    private void setUpRecyclerView(List<BroadcastModel> list) {
        list.add(0,gameItem);
        adapter = new BroadcastRcvAdapter(list,getApplication());
        binding.rcvBroadListOfGame.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvBroadListOfGame.setAdapter(adapter);
        refreshContainer.stopLoading();
    }

    public void refreshBroadcastListOfGame() {
        refreshContainer.startLoading();
        final List<BroadcastModel>list =new ArrayList<>();
        SearchTwitch searchTwitch =new SearchTwitch();
        searchTwitch.getListOfGame(0, gameItem.game.name, list, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpRecyclerView(list);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshBroadcastListOfGame();
    }
}
