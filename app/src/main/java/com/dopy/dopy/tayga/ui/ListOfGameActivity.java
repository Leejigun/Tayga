package com.dopy.dopy.tayga.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityGameDetailPageBinding;
import com.dopy.dopy.tayga.databinding.ActivityListOfGameBinding;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.twitch.SearchTwitch;
import com.dopy.dopy.tayga.model.twitch.TwitchService;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ListOfGameActivity extends AppCompatActivity {

    ActivityListOfGameBinding binding;
    BroadcastRcvAdapter adapter;
    GameItem gameItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_of_game);
        gameItem = Parcels.unwrap(getIntent().getParcelableExtra("ListOfGameActivity"));
        getSupportActionBar().setTitle(gameItem.showTitle());
        setUpRecyclerView();

        if(savedInstanceState!=null){
            adapter.setData((List<BroadcastModel>) Parcels.unwrap(savedInstanceState.getParcelable("BroadcastModelList")));
        }else{
            refreshBroadcastListOfGame(0);
        }
    }

    private void setUpRecyclerView() {
        List<BroadcastModel> list = new ArrayList<>();
        list.add(gameItem);
        adapter = new BroadcastRcvAdapter(list, this);
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
