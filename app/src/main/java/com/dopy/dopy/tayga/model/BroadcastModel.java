package com.dopy.dopy.tayga.model;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastModel {
    String title;
    String hostName;
    String countOfViewr;
    String snapshot;
    int isfavorites=0;

    public BroadcastModel(String title) {this.title=title;}

    public BroadcastModel( String title, String hostName, String countOfViewr, String snapshot,int favorites) {
        this.title = title;
        this.hostName = hostName;
        this.countOfViewr = countOfViewr;
        this.snapshot = snapshot;
        this.isfavorites=favorites;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFavorites() {
        return isfavorites;
    }

    public void setFavorites(int favorites) {
        this.isfavorites = favorites;
    }
}
