package com.dopy.dopy.tayga.model;

import android.view.View;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;

import java.util.List;

/**
 * Created by Dopy on 2017-08-22.
 */

public class TwitchListContainer extends BroadcastModel {
    final public static int STREAMER = 201;
    final public static int GAME = 202;
    int listType;
    List<TwitchStream> twitchStreamList;
    List<GameItem> gameItemList;
    String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public TwitchListContainer() {
    }

    public int getListType() {
        return listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }

    public List<TwitchStream> getTwitchStreamList() {
        return twitchStreamList;
    }

    public void setTwitchStreamList(List<TwitchStream> twitchStreamList) {
        this.twitchStreamList = twitchStreamList;
    }

    public List<GameItem> getGameItemList() {
        return gameItemList;
    }

    public void setGameItemList(List<GameItem> gameItemList) {
        this.gameItemList = gameItemList;
    }
}
