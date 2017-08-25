package com.dopy.dopy.tayga.model.twitch;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dopy on 2017-08-14.
 */

public class TwitchStreamList extends BroadcastModel{
    String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @SerializedName("streams")
    private List<TwitchStream> list;

    public List<TwitchStream> getList() {
        return list;
    }

    public void setList(List<TwitchStream> list) {
        this.list = list;
    }
}
