package com.dopy.dopy.tayga.model.twich;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dopy on 2017-08-14.
 */

public class TwitchStreamList {
    @SerializedName("streams")
    private List<TwitchStream> list;

    public List<TwitchStream> getList() {
        return list;
    }

    public void setList(List<TwitchStream> list) {
        this.list = list;
    }
}
