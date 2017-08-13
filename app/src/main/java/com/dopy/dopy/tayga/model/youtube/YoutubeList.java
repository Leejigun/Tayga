package com.dopy.dopy.tayga.model.youtube;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dopy on 2017-08-13.
 */

public class YoutubeList {
    @SerializedName("items")
    private List<SearchData> list;

    public List<SearchData> getList() {
        return list;
    }

    public void setList(List<SearchData> list) {
        this.list = list;
    }
}
