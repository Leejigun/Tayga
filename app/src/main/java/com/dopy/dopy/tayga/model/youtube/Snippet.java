package com.dopy.dopy.tayga.model.youtube;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dopy on 2017-08-15.
 */

public class Snippet {
    @SerializedName("publishedAt")
    @Expose
    public String publishedAt;
    @SerializedName("channelId")
    @Expose
    public String channelId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("thumbnails")
    @Expose
    public Thumbnails thumbnails;
    @SerializedName("channelTitle")
    @Expose
    public String channelTitle;

    public String getPublishedAt(){
        try {
            Date date = new java.text.SimpleDateFormat("yyyy-mm-dd").parse(publishedAt);
            return ("게시일: " +new SimpleDateFormat("yyyy년 mm월 dd일").format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return publishedAt;
    }
}
