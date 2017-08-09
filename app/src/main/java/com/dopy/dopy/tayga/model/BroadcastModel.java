package com.dopy.dopy.tayga.model;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastModel {
    String title;
    String hostName;
    String countOfViewr;
    String snapshot;

    public BroadcastModel() {}

    public BroadcastModel( String title, String hostName, String countOfViewr, String snapshot) {
        this.title = title;
        this.hostName = hostName;
        this.countOfViewr = countOfViewr;
        this.snapshot = snapshot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
