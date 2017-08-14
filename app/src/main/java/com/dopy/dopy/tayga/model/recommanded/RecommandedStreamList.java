package com.dopy.dopy.tayga.model.recommanded;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;

import java.util.List;

/**
 * Created by Dopy on 2017-08-14.
 */

public class RecommandedStreamList extends BroadcastModel {

    private List<BroadcastModel> list;

    public RecommandedStreamList(List<BroadcastModel> list) {
        this.list = list;
    }

    public List<BroadcastModel> getList() {
        return list;
    }

    public void setList(List<BroadcastModel> list) {
        this.list = list;
    }
}
