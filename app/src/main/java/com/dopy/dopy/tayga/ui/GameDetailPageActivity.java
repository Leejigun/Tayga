package com.dopy.dopy.tayga.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.baoyz.widget.PullRefreshLayout;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityGameDetailPageBinding;
import com.dopy.dopy.tayga.model.RefreshContainer;
import com.dopy.dopy.tayga.model.RefreshDoneInterface;
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


public class GameDetailPageActivity extends AppCompatActivity {

    final String SERVICE_KEY = "AIzaSyAzd4t2_efKUvoyzM0X49ckFYGLe9s-IjI";


    ActivityGameDetailPageBinding binding;
    BroadcastModel model;
    YouTubePlayer youtubePlayer;
    YouTubePlayerSupportFragment youtubeFragment;
    YoutubeDetailFragment youtubeDetailFragment;
    SearchData currentUtube;
    List<BroadcastModel> youtubeList;
    RefreshContainer refreshContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game_detail_page);
        model = Parcels.unwrap(getIntent().getParcelableExtra("GameDetailPageActivity"));
        refreshContainer = new RefreshContainer(binding.rotateGamelading, binding.containerGameDetailFragment, binding.containerrotateGamelading);
        refreshContainer.pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshYouTubeModelList(model);
            }
        });
        binding.toolbarGameDetail.setTitle("Tayga");
        binding.toolbarGameDetail.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(binding.toolbarGameDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hookDraggablePanelListeners();
        initializeYoutubeFragment();
        initializeDraggablePanel();


        refreshYouTubeModelList(model);
    }

    private void refreshYouTubeModelList(BroadcastModel model) {
        refreshContainer.startLoading();
        final List<BroadcastModel> broadcastModelList = new ArrayList<>();
        broadcastModelList.add(model);
        String type = model.getClass().toString();
        if (TwitchStream.class.toString().equals(type)) {
            TwitchStream twitchStream = (TwitchStream) model;
            SearchYoutube searchYoutube = new SearchYoutube();
            searchYoutube.getUtube(twitchStream.channel.displayName, 3, broadcastModelList, new RefreshDoneInterface() {
                @Override
                public void refreshDone() {
                    setUpRecyclerView(broadcastModelList);
                }
            });
        }
    }

    private void setUpRecyclerView(List<BroadcastModel> list) {
        YoutubeRcvAdapter adapter = new YoutubeRcvAdapter(list, getApplication());
        adapter.addSetOnClickListener(new SetOnclickYoutubePlay() {
            @Override
            public void onClickYoutube(View v, SearchData data) {
                Log.d("GameDetailPageActivity", "call onClickYoutube");
                currentUtube = data;
                LoadYoutube(currentUtube);
            }
        });
        binding.rcvGameDetail.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvGameDetail.setAdapter(adapter);
        refreshContainer.stopLoading();
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
