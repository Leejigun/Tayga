package com.dopy.dopy.tayga.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityGameDetailPageBinding;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
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

    final String SERVICE_KEY = "AIzaSyAzd4t2_efKUvoyzM0X49ckFYGLe9s-IjI";


    ActivityGameDetailPageBinding binding;

    YouTubePlayer youtubePlayer;
    YouTubePlayerSupportFragment youtubeFragment;
    YoutubeDetailFragment youtubeDetailFragment;
    YoutubeRcvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =DataBindingUtil.setContentView(this, R.layout.activity_game_detail_page);
        BroadcastModel model = Parcels.unwrap(getIntent().getParcelableExtra("GameDetailPageActivity"));
        setUpRecyclerView(model);
        initializeYoutubeFragment();
        initializeDraggablePanel();
        hookDraggablePanelListeners();
    }

    private void setUpRecyclerView(BroadcastModel model) {
        List<BroadcastModel> youtubeList = new ArrayList<>();
        youtubeList.add(model);
        adapter = new YoutubeRcvAdapter(youtubeList);
        refreshYouTubeModelList(model, adapter);
        binding.rcvGameDetail.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvGameDetail.setAdapter(adapter);

        /*adapter.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View view, int i) {
                SearchData data = adapter.getData().get(i);
                LoadYoutube(data);
            }
        });*/
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
        String type = model.getClass().toString();
        if(TwitchStream.class.toString().equals(type)){
            Log.d("GameDetailPageActivity","call refreshYouTubeModelList");
            TwitchStream twitchStream = (TwitchStream)model;
            searchYoutube.getUtube(twitchStream.channel.displayName, 10, adapter);
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
