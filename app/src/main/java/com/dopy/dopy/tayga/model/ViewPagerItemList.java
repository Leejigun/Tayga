package com.dopy.dopy.tayga.model;

import android.view.View;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;

import java.util.List;

/**
 * Created by Dopy on 2017-08-22.
 */

public class ViewPagerItemList extends BroadcastModel{
    List<BroadcastModel> models;

    public ViewPagerItemList(List<BroadcastModel> models) {
        this.models = models;
    }

    public List<BroadcastModel> getModels() {
        return models;
    }

    public void setModels(List<BroadcastModel> models) {
        this.models = models;
    }
}
