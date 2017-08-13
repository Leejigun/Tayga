package com.dopy.dopy.tayga.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityGameDetailPageBinding;
import com.dopy.dopy.tayga.databinding.VideoClipHeaderBinding;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.youtube.SearchData;
import com.dopy.dopy.tayga.model.youtube.SearchYoutube;
import com.dopy.dopy.tayga.model.youtube.YoutubeRcvAdapter;
import com.github.pedrovgs.DraggableListener;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;


public class GameDetailPageActivity extends AppCompatActivity {
    final String BASE_URL = "https://www.googleapis.com/youtube/v3/";

    final String SERVICE_KEY = "AIzaSyAzd4t2_efKUvoyzM0X49ckFYGLe9s-IjI";


    ActivityGameDetailPageBinding binding;
    BroadcastModel model;

    YouTubePlayer youtubePlayer;
    YouTubePlayerSupportFragment youtubeFragment;
    YoutubeDetailFragment youtubeDetailFragment;
    List<SearchData> youtubelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game_detail_page);
        Intent intent = getIntent();
        model = (BroadcastModel) intent.getParcelableExtra(BroadcastModel.class.toString());
        setUpParallaxRecyclerView(model);
        initializeYoutubeFragment();
        initializeDraggablePanel();
        hookDraggablePanelListeners();

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


    private void refreshYouTubeModelList(BroadcastModel model,List<SearchData> list,YoutubeRcvAdapter adapter) {
        SearchYoutube searchYoutube= new SearchYoutube(list,adapter);
        searchYoutube.getUtube(model.getTag(),10);
         }

    private void setUpParallaxRecyclerView(BroadcastModel model) {
        List<SearchData> youtubeList=new ArrayList<>();
        final YoutubeRcvAdapter adapter = new YoutubeRcvAdapter(youtubeList, this);
        binding.rcvGameDetail.setLayoutManager(new LinearLayoutManager(this));
        View header = LayoutInflater.from(this).inflate(R.layout.video_clip_header, binding.rcvGameDetail, false);
        VideoClipHeaderBinding headerBinding = VideoClipHeaderBinding.bind(header);
        headerBinding.imvGameDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "방송 보러가기", Toast.LENGTH_LONG).show();
            }
        });
        headerBinding.setDetailModel(model);
        switch (model.getTag()) {
            case BroadcastModel.HEARTHSTONE:
                Glide.with(binding.getRoot()).load(R.drawable.gamesnapshot).into(headerBinding.imvGameDetail);
                break;
            case BroadcastModel.BATTLE_GROUND:
                Glide.with(binding.getRoot()).load(R.drawable.gamenapshot2).into(headerBinding.imvGameDetail);
                break;
            case BroadcastModel.OVERWATCH:
                Glide.with(binding.getRoot()).load(R.drawable.gamenapshot3).into(headerBinding.imvGameDetail);
                break;
        }
        adapter.setParallaxHeader(header, binding.rcvGameDetail);
        binding.rcvGameDetail.setAdapter(adapter);
        refreshYouTubeModelList(model ,youtubeList,adapter);
        adapter.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View view, int i) {
                SearchData data=adapter.getData().get(i);
                LoadYoutube(data);
            }
        });
    }
    public void LoadYoutube(SearchData data){
        youtubePlayer.loadVideo(data.id.videoId);
        youtubeDetailFragment.setDetailData(data);
        binding.draggablePanel.setVisibility(View.VISIBLE);
        binding.draggablePanel.maximize();
    }

    @Override
    public void onBackPressed() {
        if(binding.draggablePanel.getVisibility()==GONE){
            finish();
        }
        else if(binding.draggablePanel.isMaximized()){
            binding.draggablePanel.minimize();
        }else if(binding.draggablePanel.isMinimized()){
            binding.draggablePanel.closeToRight();
        }
        else{
            finish();
        }
    }
}
