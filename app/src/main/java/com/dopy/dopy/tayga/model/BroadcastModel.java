package com.dopy.dopy.tayga.model;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastModel {
    int viewtype;
    String title;
    String hostName;
    String countOfViewr;
    String snapshot;

    public BroadcastModel(int viewtype) {
        this.viewtype = viewtype;
    }

    public BroadcastModel(int viewtype, String title, String hostName, String countOfViewr, String snapshot) {
        this.viewtype = viewtype;
        this.title = title;
        this.hostName = hostName;
        this.countOfViewr = countOfViewr;
        this.snapshot = snapshot;
    }

    public int getViewtype() {
        return viewtype;
    }

    public void setViewtype(int viewtype) {
        this.viewtype = viewtype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
