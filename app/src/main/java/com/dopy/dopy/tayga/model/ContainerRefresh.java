package com.dopy.dopy.tayga.model;

import android.view.View;
import android.widget.FrameLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.victor.loading.rotate.RotateLoading;

/**
 * Created by Dopy on 2017-08-17.
 */

public class ContainerRefresh {
    public RotateLoading rotateLoading;
    public PullRefreshLayout pullRefreshLayout;
    public FrameLayout containerRotated;

    public ContainerRefresh(RotateLoading rotateLoading, PullRefreshLayout pullRefreshLayout,FrameLayout containerRotated) {
        this.rotateLoading = rotateLoading;
        this.pullRefreshLayout = pullRefreshLayout;
        this.containerRotated=containerRotated;
        containerRotated.setVisibility(View.VISIBLE);
        rotateLoading.start();
    }

    public void stopLoading(){
        containerRotated.setVisibility(View.GONE);
        rotateLoading.stop();
        pullRefreshLayout.setRefreshing(false);
    }
}
