
package com.dopy.dopy.tayga.model.twitch;

import android.view.View;
import android.widget.Toast;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Parcel
public class TwitchStream extends BroadcastModel{
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int startCount;
    @SerializedName("viewers")
    @Expose
    public double viewers;
    @SerializedName("preview")
    @Expose
    public Preview preview;
    @SerializedName("channel")
    @Expose
    public Channel channel;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("_links")
    @Expose
    public Links_ links;

    public String showCreatedAt(){
        try {
            Date date = new java.text.SimpleDateFormat("mm-dd-hh-mm").parse(createdAt);
            return (new SimpleDateFormat("mm월 dd일- hh시 mm분").format(date)+"방송 시작");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return createdAt;
    }

    public String showTitle() {
        return channel.status;
    }


    public String showHostName() {
        return channel.displayName;
    }

    public String showViewrToString() {
        int viewer=(int) viewers;
        return (viewer +"명 시청중");
    }
    public String showFollwersToString() {
        int viewer=(int) channel.followers;
        return (viewer +"명이 팔로윙중");
    }
    public String showGameToString() {
        String name = channel.game;
        return ("저번 즐겨찾기 때 [ "+name+" ] 방송을 하던 스트리머입니다.");
    }public String showCurrentPlayGameToString() {
        String name = channel.game;
        return (name+" 플레이 중..");
    }


    @Parcel
    public static class Channel {
        @SerializedName("_id")
        @Expose
        public double id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("display_name")
        @Expose
        public String displayName;
        @SerializedName("game")
        @Expose
        public String game;
        @SerializedName("language")
        @Expose
        public String language;
        @SerializedName("logo")
        @Expose
        public String logo;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("_links")
        @Expose
        public Links links;
        @SerializedName("followers")
        @Expose
        public double followers;
    }
    @Parcel
    public static class Preview {

        @SerializedName("small")
        @Expose
        public String small;
        @SerializedName("medium")
        @Expose
        public String medium;

    }
    @Parcel
    public static class Links_ {

        @SerializedName("self")
        @Expose
        public String self;

    }
    @Parcel
    public static class Links {
        @SerializedName("self")
        @Expose
        public String self;
        @SerializedName("follows")
        @Expose
        public String follows;
        @SerializedName("commercial")
        @Expose
        public String commercial;
        @SerializedName("stream_key")
        @Expose
        public String streamKey;
        @SerializedName("chat")
        @Expose
        public String chat;
        @SerializedName("features")
        @Expose
        public String features;
        @SerializedName("subscriptions")
        @Expose
        public String subscriptions;
        @SerializedName("editors")
        @Expose
        public String editors;
        @SerializedName("teams")
        @Expose
        public String teams;
        @SerializedName("videos")
        @Expose
        public String videos;

    }

}
