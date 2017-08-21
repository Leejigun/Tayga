package com.dopy.dopy.tayga.model.twitch;

/**
 * Created by Dopy on 2017-08-21.
 */

public class Star {
    String streamerName;
    int starCount;

    public Star() {
    }

    public Star(String streamerName, int starCount) {
        this.streamerName = streamerName;
        this.starCount = starCount;
    }

    public String getStreamerName() {
        return streamerName;
    }

    public void setStreamerName(String streamerName) {
        this.streamerName = streamerName;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }
}
