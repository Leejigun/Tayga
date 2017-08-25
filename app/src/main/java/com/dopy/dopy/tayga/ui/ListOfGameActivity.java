package com.dopy.dopy.tayga.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;

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
        Log.d(TAG, "onCreate");
        refreshContainer = new RefreshContainer(binding.loadingGameOList, binding.listOfGmaeRefreshLayout, binding.containerloadingGameOList);
        refreshContainer.pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshBroadcastListOfGame();
            }
        });
        gameItem = Parcels.unwrap(getIntent().getParcelableExtra("ListOfGameActivity"));
        binding.toolbarListOfGame.setTitle("Tayga");
        binding.toolbarListOfGame.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(binding.toolbarListOfGame);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        refreshBroadcastListOfGame();
    }

    private void setUpRecyclerView(List<BroadcastModel> list) {
        list.add(1, gameItem);
        adapter = new BroadcastRcvAdapter(list, getApplication());
        binding.rcvBroadListOfGame.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvBroadListOfGame.setAdapter(adapter);
        refreshContainer.stopLoading();
    }

    public void refreshBroadcastListOfGame() {
        refreshContainer.startLoading();
        final List<BroadcastModel> list = new ArrayList<>();
        setUpPopularSteam(list);
    }
    private void setUpPopularSteam(final List<BroadcastModel> broadcastModels){
        SearchTwitch searchTwitch =new SearchTwitch();
        String tag = "실시간 최고 시청자 방송";
        searchTwitch.getTwitch(0, 1, broadcastModels,tag,0, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpFirst10Items(broadcastModels);
            }
        });
    }


    private void setUpFirst10Items(final List<BroadcastModel> broadcastModels) {
        SearchTwitch searchTwitch = new SearchTwitch();
        searchTwitch.getListOfGame(0, 10, gameItem.game.name, broadcastModels, null, 1, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpAdvertisePopularGameItem(broadcastModels);
            }
        });
    }
    private void setUpAdvertisePopularGameItem(final List<BroadcastModel> broadcastModels){
        SearchTwitch searchTwitch = new SearchTwitch();
        String tag = "이 게임은 어떤가요? (시청자수 Top 1)";
        searchTwitch.getAdvertiseGame(0,broadcastModels,tag,2, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpLast10Items(broadcastModels);
            }
        });
    }
    private void setUpLast10Items(final List<BroadcastModel> broadcastModels) {
        SearchTwitch searchTwitch = new SearchTwitch();
        String tag = "Top 11 ~ Top 20";
        searchTwitch.getListOfGame(10, 20, gameItem.game.name, broadcastModels, tag, 3, new RefreshDoneInterface() {
            @Override
            public void refreshDone() {
                setUpRecyclerView(broadcastModels);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshBroadcastListOfGame();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
