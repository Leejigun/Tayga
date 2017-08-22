package com.dopy.dopy.tayga.model;

import android.view.View;
import android.widget.FrameLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.victor.loading.rotate.RotateLoading;

/**
 * Created by Dopy on 2017-08-17.
 */

public class RefreshContainer {
    public RotateLoading rotateLoading;
    public PullRefreshLayout pullRefreshLayout;
    public FrameLayout containerRotated;

    public RefreshContainer(RotateLoading rotateLoading, PullRefreshLayout pullRefreshLayout, FrameLayout containerRotated) {
        this.rotateLoading = rotateLoading;
        this.pullRefreshLayout = pullRefreshLayout;
        this.containerRotated=containerRotated;
    }
    public void startLoading(){
        containerRotated.setVisibility(View.VISIBLE);
        rotateLoading.start();
    }

    public void stopLoading(){
        containerRotated.setVisibility(View.GONE);
        rotateLoading.stop();
        pullRefreshLayout.setRefreshing(false);
    }
}
