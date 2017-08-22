package com.dopy.dopy.tayga.model;

import android.view.View;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;

import java.util.List;

/**
 * Created by Dopy on 2017-08-22.
 */

public class GridItemList extends BroadcastModel {

    List<BroadcastModel> models;

    public GridItemList(List<BroadcastModel> models) {
        this.models = models;
    }

    public List<BroadcastModel> getModels() {
        return models;
    }

    public void setModels(List<BroadcastModel> models) {
        this.models = models;
    }

    @Override
    public String showTitle() {
        return null;
    }

    @Override
    public String showHostName() {
        return null;
    }

    @Override
    public String showViewrToString() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
