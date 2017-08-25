package com.dopy.dopy.tayga.model;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;

/**
 * Created by Dopy on 2017-08-25.
 */

public class AdvertiseCard extends BroadcastModel {
    String tag;
    BroadcastModel adItem; // Broadcast or Game

    public AdvertiseCard() {
    }

    public AdvertiseCard(String tag, BroadcastModel adItem) {
        this.tag = tag;
        this.adItem = adItem;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public BroadcastModel getAdItem() {
        return adItem;
    }

    public void setAdItem(BroadcastModel adItem) {
        this.adItem = adItem;
    }
}
