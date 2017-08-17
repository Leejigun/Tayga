package com.dopy.dopy.tayga.ui;

import com.baoyz.widget.PullRefreshLayout;
import com.victor.loading.rotate.RotateLoading;

/**
 * Created by Dopy on 2017-08-17.
 */

public class ContainerRefresh {
    public RotateLoading rotateLoading;
    public PullRefreshLayout pullRefreshLayout;

    public ContainerRefresh(RotateLoading rotateLoading, PullRefreshLayout pullRefreshLayout) {
        this.rotateLoading = rotateLoading;
        this.pullRefreshLayout = pullRefreshLayout;
        rotateLoading.start();
    }

    public void stopLoading(){
        rotateLoading.stop();
        pullRefreshLayout.setRefreshing(false);
    }
}
