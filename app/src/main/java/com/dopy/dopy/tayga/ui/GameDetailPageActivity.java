package com.dopy.dopy.tayga.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityGameDetailPageBinding;
import com.dopy.dopy.tayga.databinding.VideoClipHeaderBinding;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.twich.TwitchStream;
import com.dopy.dopy.tayga.model.youtube.SearchData;
import com.dopy.dopy.tayga.model.youtube.SearchYoutube;
import com.dopy.dopy.tayga.model.youtube.YouTubeClickInterface;
import com.dopy.dopy.tayga.model.youtube.YoutubeRcvAdapter;
import com.github.pedrovgs.DraggableListener;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;


public class GameDetailPageActivity extends AppCompatActivity {
    final String BASE_URL = "https://www.googleapis.com/youtube/v3/";

    final String SERVICE_KEY = "AIzaSyAzd4t2_efKUvoyzM0X49ckFYGLe9s-IjI";


    ActivityGameDetailPageBinding binding;

    YouTubePlayer youtubePlayer;
    YouTubePlayerSupportFragment youtubeFragment;
    YoutubeDetailFragment youtubeDetailFragment;
    YoutubeRcvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game_detail_page);
        BroadcastModel model = Parcels.unwrap(getIntent().getParcelableExtra("Stream"));
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


    private void refreshYouTubeModelList(BroadcastModel model, YoutubeRcvAdapter adapter) {
        SearchYoutube searchYoutube = new SearchYoutube();
        switch (model.getClass().toString()) {
            case "TwitchStream":
                TwitchStream twitchStream = (TwitchStream)model;
                searchYoutube.getUtube(twitchStream.channel.game, 10, adapter);
                break;
        }
    }

    private void setUpParallaxRecyclerView(BroadcastModel model) {
        List<SearchData> youtubeList = new ArrayList<>();
        adapter = new YoutubeRcvAdapter(youtubeList, new YouTubeClickInterface() {
            @Override
            public void itemClick(View v) {
                Toast.makeText(v.getContext(),"Clicked Item",Toast.LENGTH_LONG).show();
            }
        });
        binding.rcvGameDetail.setLayoutManager(new LinearLayoutManager(this));
        View header = LayoutInflater.from(this).inflate(R.layout.video_clip_header, binding.rcvGameDetail, false);
        VideoClipHeaderBinding headerBinding = VideoClipHeaderBinding.bind(header);
        headerBinding.setTwichStraemModel((TwitchStream) model);
        binding.rcvGameDetail.setAdapter(adapter);
        refreshYouTubeModelList(model, adapter);

        /*adapter.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View view, int i) {
                SearchData data = adapter.getData().get(i);
                LoadYoutube(data);
            }
        });*/
    }
    public void onYoutubeItemClicked(View v){

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
