package com.dopy.dopy.tayga.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityGameDetailPageBinding;
import com.dopy.dopy.tayga.model.ContainerRefresh;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.dopy.dopy.tayga.model.youtube.SearchData;
import com.dopy.dopy.tayga.model.youtube.SearchYoutube;
import com.dopy.dopy.tayga.model.youtube.SetOnclickYoutubePlay;
import com.dopy.dopy.tayga.model.youtube.YoutubeRcvAdapter;
import com.github.pedrovgs.DraggableListener;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;


public class GameDetailPageActivity extends AppCompatActivity{

    final String SERVICE_KEY = "AIzaSyAzd4t2_efKUvoyzM0X49ckFYGLe9s-IjI";


    ActivityGameDetailPageBinding binding;

    YouTubePlayer youtubePlayer;
    YouTubePlayerSupportFragment youtubeFragment;
    YoutubeDetailFragment youtubeDetailFragment;
    YoutubeRcvAdapter adapter;
    SearchData currentUtube;
    List<BroadcastModel> youtubeList;
    ContainerRefresh containerRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =DataBindingUtil.setContentView(this, R.layout.activity_game_detail_page);
        containerRefresh =new ContainerRefresh(binding.rotateGamelading,binding.containerGameDetailFragment);
        BroadcastModel model = Parcels.unwrap(getIntent().getParcelableExtra("GameDetailPageActivity"));
        getSupportActionBar().setTitle(model.showTitle());
        setUpRecyclerView(model);
        hookDraggablePanelListeners();
        initializeYoutubeFragment();
        initializeDraggablePanel();

        /*if(savedInstanceState!=null){
            adapter.restoreData((List<BroadcastModel>) Parcels.unwrap(savedInstanceState.getParcelable("BroadcastBodelList")));

            String state = savedInstanceState.getString("State");
            binding.draggablePanel.setVisibility(View.VISIBLE);
            if(!state.equals("GONE")) {
                if (state.equals("MAX")) {
                    binding.draggablePanel.maximize();
                } else {
                    binding.draggablePanel.minimize();
                }
                Boolean isPlaying = savedInstanceState.getBoolean("isPlaying");
                if(isPlaying){
                    LoadYoutube(currentUtube);
                }else{
                    LoadYoutube(currentUtube);
                    youtubePlayer.pause();
                }
            }

        }else{
            //not exist saveData
            youtubeList = new ArrayList<>();
            refreshYouTubeModelList(model);
        }*/
        refreshYouTubeModelList(model);
    }

    private void setUpRecyclerView(BroadcastModel model) {
        youtubeList = new ArrayList<>();
        youtubeList.add(model);
        adapter = new YoutubeRcvAdapter(youtubeList , containerRefresh);
        adapter.addSetOnClickListener(new SetOnclickYoutubePlay() {
            @Override
            public void onClickYoutube(View v, SearchData data) {
                Log.d("GameDetailPageActivity","call onClickYoutube");
                currentUtube=data;
                LoadYoutube(currentUtube);
            }
        });
        binding.rcvGameDetail.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvGameDetail.setAdapter(adapter);
    }

    private void initializeYoutubeFragment() {
        youtubeFragment = new YouTubePlayerSupportFragment();
        youtubeFragment.initialize(SERVICE_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    youtubePlayer = player;
                    youtubePlayer.setShowFullscreenButton(true);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult error) {
            }
        });
    }

    private void initializeDraggablePanel() {
        binding.draggablePanel.setFragmentManager(getSupportFragmentManager());
        binding.draggablePanel.setTopFragment(youtubeFragment);
        youtubeDetailFragment = YoutubeDetailFragment.newInstance(null);
        binding.draggablePanel.setBottomFragment(youtubeDetailFragment);
        binding.draggablePanel.initializeView();
        binding.draggablePanel.setVisibility(GONE);
    }

    private void hookDraggablePanelListeners() {
        binding.draggablePanel.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                playVideo();
            }

            @Override
            public void onMinimized() {
                //Empty
            }

            @Override
            public void onClosedToLeft() {
                pauseVideo();
            }

            @Override
            public void onClosedToRight() {
                pauseVideo();
            }
        });
    }

    private void pauseVideo() {
        if (youtubePlayer.isPlaying()) {
            youtubePlayer.pause();
        }
    }

    /**
     * Resume the video reproduced in the YouTubePlayer.
     */
    private void playVideo() {
        if (!youtubePlayer.isPlaying()) {
            youtubePlayer.play();
        }
    }


    private void refreshYouTubeModelList(BroadcastModel model) {
        SearchYoutube searchYoutube = new SearchYoutube();
        String type = model.getClass().toString();
        if(TwitchStream.class.toString().equals(type)){
            Log.d("GameDetailPageActivity","call refreshYouTubeModelList");
            TwitchStream twitchStream = (TwitchStream)model;
            searchYoutube.getUtube(twitchStream.channel.displayName, 10, adapter);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("BroadcastBodelList",Parcels.wrap(adapter.getData()));
        outState.putParcelable("CurrentVideo",Parcels.wrap(currentUtube));
        if(binding.draggablePanel.getVisibility()==View.GONE){
            outState.putString("State","GONE");
        }else{
            if(binding.draggablePanel.isMaximized()){
                outState.putString("State","MAX");
            }else{
                outState.putString("State","MIN");
            }
            if(youtubePlayer.isPlaying()){
                outState.putBoolean("isPlaying",true);
            }else{
                outState.putBoolean("isPlaying",false);
            }
        }
    }

    public void LoadYoutube(SearchData data) {
        youtubePlayer.loadVideo(data.id.videoId);
        youtubeDetailFragment.setDetailData(data);
        binding.draggablePanel.setVisibility(View.VISIBLE);
        binding.draggablePanel.maximize();
    }

    @Override
    public void onBackPressed() {
        if (binding.draggablePanel.getVisibility() == GONE) {
            finish();
        } else if (binding.draggablePanel.isMaximized()) {
            binding.draggablePanel.minimize();
        } else if (binding.draggablePanel.isMinimized()) {
            binding.draggablePanel.closeToRight();
        } else {
            finish();
        }
    }
}
