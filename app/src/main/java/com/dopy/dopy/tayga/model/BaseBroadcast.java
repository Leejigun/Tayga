package com.dopy.dopy.tayga.model;

/**
 * Created by Dopy on 2017-08-04.
 */

public class BaseBroadcast {
    String hostName;
    String title;
    String snepshotURL;
    String linkURL;
    int viewerCount;

    public String getHostName() {
        return hostName;
    }

    public String getTitle() {
        return title;
    }

    public String getSnepshotURL() {
        return snepshotURL;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public int getViewerCount() {
        return viewerCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
