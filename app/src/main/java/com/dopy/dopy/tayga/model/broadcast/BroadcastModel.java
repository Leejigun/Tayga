package com.dopy.dopy.tayga.model.broadcast;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.dopy.dopy.tayga.ui.GameDetailPageActivity;

import java.net.URL;

/**
 * Created by Dopy on 2017-08-08.
 */

public abstract class BroadcastModel{
    public BroadcastModel() {
    }

    public abstract String showTitle();

    public abstract String showHostName();

    public abstract String showViewrToString();

    public abstract void onClick(View v);
}