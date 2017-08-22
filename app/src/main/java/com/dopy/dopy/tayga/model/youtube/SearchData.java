package com.dopy.dopy.tayga.model.youtube;

import android.os.Parcel;
import android.view.View;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dopy on 2017-08-12.
 */
@org.parceler.Parcel
public class SearchData extends BroadcastModel {
    @SerializedName("id")
    @Expose
    public Id id;
    @SerializedName("snippet")
    @Expose
    public Snippet snippet;


    public String showTitle() {
        return snippet.title;
    }

    public String showHostName() {
        return snippet.channelTitle;
    }

    public String showViewrToString() {
        return null;
    }

    public void onClick(View v) {


    }

    @org.parceler.Parcel
    public static class Id {

        @SerializedName("videoId")
        @Expose
        public String videoId;
    }

    @org.parceler.Parcel
    public static class Snippet {
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

        @org.parceler.Parcel
        public static class Thumbnails {
            @SerializedName("default")
            @Expose
            public Default _default;
            @SerializedName("medium")
            @Expose
            public Medium medium;
            @SerializedName("high")
            @Expose
            public High high;

            @org.parceler.Parcel
            public static class Default {

                @SerializedName("url")
                @Expose
                public String url;
                @SerializedName("width")
                @Expose
                public Integer width;
                @SerializedName("height")
                @Expose
                public Integer height;

            }

            @org.parceler.Parcel
            public static class Medium {

                @SerializedName("url")
                @Expose
                public String url;
                @SerializedName("width")
                @Expose
                public Integer width;
                @SerializedName("height")
                @Expose
                public Integer height;

            }

            @org.parceler.Parcel
            public static class High {

                @SerializedName("url")
                @Expose
                public String url;
                @SerializedName("width")
                @Expose
                public Integer width;
                @SerializedName("height")
                @Expose
                public Integer height;
            }
        }

        public String getPublishedAt() {
            try {
                Date date = new java.text.SimpleDateFormat("yyyy-mm-dd").parse(publishedAt);
                return ("게시일: " + new SimpleDateFormat("yyyy년 mm월 dd일").format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return publishedAt;
        }
    }
}
