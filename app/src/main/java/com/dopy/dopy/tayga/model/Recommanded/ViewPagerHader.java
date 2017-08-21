package com.dopy.dopy.tayga.model.Recommanded;

import android.view.View;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;

/**
 * Created by Dopy on 2017-08-21.
 */

public class ViewPagerHader extends BroadcastModel {
    TwitchStream topOfViewr;
    TwitchStream TopOfStar;
    TwitchStream TopOfFavorites;
    GameItem TopOfStarGame;
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
