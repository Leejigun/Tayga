package com.dopy.dopy.tayga.model.game;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dopy on 2017-08-15.
 */

public class GameItemList {
    @SerializedName("top")
    public List<GameItem> list;

    public List<GameItem> getList() {
        return list;
    }

    public void setList(List<GameItem> list) {
        this.list = list;
    }
}
