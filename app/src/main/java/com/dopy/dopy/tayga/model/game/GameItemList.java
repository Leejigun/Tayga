package com.dopy.dopy.tayga.model.game;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dopy on 2017-08-15.
 */

public class GameItemList extends BroadcastModel{
    @SerializedName("top")
    public List<GameItem> list;

    String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<GameItem> getList() {
        return list;
    }

    public void setList(List<GameItem> list) {
        this.list = list;
    }
}
