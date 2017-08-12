package com.dopy.dopy.tayga.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityGameDetailPageBinding;
import com.dopy.dopy.tayga.model.BroadcastModel;
import com.dopy.dopy.tayga.model.BroadcastRcvAdapter;
import com.github.pedrovgs.DraggableListener;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;
import java.util.List;


public class GameDetailPageActivity extends AppCompatActivity {
    private static final String YOUTUBE_API_KEY = "AIzaSyAzd4t2_efKUvoyzM0X49ckFYGLe9s-IjI";
    private static final String VIDEO_KEY = "gsjtg7m1MMM";

    private static final String VIDEO_POSTER_TITLE = "X-Men: Days of Future Past";
    ActivityGameDetailPageBinding binding;
    BroadcastModel model;

    YouTubePlayer youtubePlayer;
    YouTubePlayerSupportFragment youtubeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game_detail_page);

        Intent intent = getIntent();
        model = (BroadcastModel)intent.getParcelableExtra(BroadcastModel.class.toString());
        setUpDetailData(model);
        //setUpParallaxRecyclerView();
        initializeYoutubeFragment();
        initializeDraggablePanel();
        hookDraggablePanelListeners();

    }
    private void initializeYoutubeFragment() {
        youtubeFragment = new YouTubePlayerSupportFragment();
        youtubeFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    youtubePlayer = player;
                    youtubePlayer.loadVideo(VIDEO_KEY);
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
        MoviePosterFragment moviePosterFragment = new MoviePosterFragment();
        binding.draggablePanel.setBottomFragment(moviePosterFragment);
        binding.draggablePanel.initializeView();
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


    private List<BroadcastModel> refreshBroadcastModelList() {
        return inputTestData();
    }

    private List<BroadcastModel> refreshRecommendedBroadcast() {
        return inputTestData();
    }

    private void setUpDetailData(BroadcastModel model){
        binding.setDetailModel(model);
        switch (model.getTag()) {
            case BroadcastModel.HEARTHSTONE:
                Glide.with(binding.getRoot()).load(R.drawable.gamesnapshot).into(binding.imvGameDetail);
                break;
            case BroadcastModel.BATTLE_GROUND:
                Glide.with(binding.getRoot()).load(R.drawable.gamenapshot2).into(binding.imvGameDetail);
                break;
            case BroadcastModel.OVERWATCH:
                Glide.with(binding.getRoot()).load(R.drawable.gamenapshot3).into(binding.imvGameDetail);
                break;
        }
    }

    private void setUpParallaxRecyclerView() {
        List<BroadcastModel> broadcastModelList = refreshBroadcastModelList();
        BroadcastRcvAdapter adapter = new BroadcastRcvAdapter(broadcastModelList, this);
        binding.rcvGameDetail.setLayoutManager(new LinearLayoutManager(this));
        View header = LayoutInflater.from(this).inflate(R.layout.video_clip_header,binding.rcvGameDetail,false);
        adapter.setParallaxHeader(header, binding.rcvGameDetail);
        binding.rcvGameDetail.setAdapter(adapter);
    }

    private List<BroadcastModel> inputTestData() {
        List<BroadcastModel> models = new ArrayList<>();
        models.add(new BroadcastModel("하스스톤 신규 확장팩 얼어붙은 왕좌의 기사들", "태상", 5600, null, "hearthstone", 0));
        models.add(new BroadcastModel("켠김에 왕까지 배틀그라운드 딩셉션과 함께", "딩셉션", 7852, null, "battleground", 0));
        models.add(new BroadcastModel("오버워치 첼린저 미로의 고급 시계 교육방송", "미로", 4230, null, "overwatch", 0));
        models.add(new BroadcastModel("하스스톤 신규 확장팩 얼어붙은 왕좌의 기사들", "태상", 5600, null, "hearthstone", 0));
        models.add(new BroadcastModel("켠김에 왕까지 배틀그라운드 딩셉션과 함께", "딩셉션", 7852, null, "battleground", 0));
        models.add(new BroadcastModel("오버워치 첼린저 미로의 고급 시계 교육방송", "미로", 4230, null, "overwatch", 0));
        models.add(new BroadcastModel("하스스톤 신규 확장팩 얼어붙은 왕좌의 기사들", "태상", 5600, null, "hearthstone", 0));
        models.add(new BroadcastModel("켠김에 왕까지 배틀그라운드 딩셉션과 함께", "딩셉션", 7852, null, "battleground", 0));
        models.add(new BroadcastModel("오버워치 첼린저 미로의 고급 시계 교육방송", "미로", 4230, null, "overwatch", 0));
        models.add(new BroadcastModel("하스스톤 신규 확장팩 얼어붙은 왕좌의 기사들", "태상", 5600, null, "hearthstone", 0));
        models.add(new BroadcastModel("켠김에 왕까지 배틀그라운드 딩셉션과 함께", "딩셉션", 7852, null, "battleground", 0));
        models.add(new BroadcastModel("오버워치 첼린저 미로의 고급 시계 교육방송", "미로", 4230, null, "overwatch", 0));
        return models;
    }

}
