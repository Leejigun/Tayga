package com.dopy.dopy.tayga.model.youtube;

import android.content.Intent;
import android.os.Parcel;
import android.provider.MediaStore;
import android.view.View;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.ui.GameDetailPageActivity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dopy on 2017-08-12.
 */

public class SearchData extends BroadcastModel {
    @SerializedName("id")
    @Expose
    public Id id;
    @SerializedName("snippet")
    @Expose
    public Snippet snippet;


    @Override
    public String showTitle() {
        return snippet.title;
    }

    @Override
    public String showHostName() {
        return snippet.channelTitle;
    }

    @Override
    public String showViewrToString() {
        return null;
    }

    @Override
    public void onClick(View v) {


    }
}
