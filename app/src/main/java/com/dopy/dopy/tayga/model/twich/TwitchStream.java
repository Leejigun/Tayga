package com.dopy.dopy.tayga.model.twich;

import android.content.Intent;
import android.view.View;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.ui.GameDetailPageActivity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcels;

/**
 * Created by Dopy on 2017-08-14.
 */

public class TwitchStream extends BroadcastModel {

    @SerializedName("_id")
    @Expose
    public double id;
    @SerializedName("game")
    @Expose
    public String game;
    @SerializedName("viewers")
    @Expose
    public Integer viewers;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("preview")
    @Expose
    public Preview preview;
    @SerializedName("channel")
    @Expose
    public Channel channel;
    @SerializedName("_links")
    @Expose
    public Links_ links;

    public String ToStringViewrs() {
        return (Integer.toString(viewers) + "명 시청중");
    }

    public void onStreamingClick(View v){

        Intent intent =new Intent(v.getContext(),GameDetailPageActivity.class);
        intent.putExtra("Stream", Parcels.wrap(this));
        v.getContext().startActivity(intent);
    }

    public class Preview {

        @SerializedName("small")
        @Expose
        public String small;
        @SerializedName("medium")
        @Expose
        public String medium;
        @SerializedName("large")
        @Expose
        public String large;
        @SerializedName("template")
        @Expose
        public String template;

    }

    public class Links_ {

        @SerializedName("self")
        @Expose
        public String self;

    }

    public class Links {

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

    public class Channel {

        @SerializedName("mature")
        @Expose
        public Boolean mature;
        @SerializedName("partner")
        @Expose
        public Boolean partner;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("broadcaster_language")
        @Expose
        public String broadcasterLanguage;
        @SerializedName("display_name")
        @Expose
        public String displayName;
        @SerializedName("game")
        @Expose
        public String game;
        @SerializedName("language")
        @Expose
        public String language;
        @SerializedName("_id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;
        @SerializedName("delay")
        @Expose
        public Object delay;
        @SerializedName("logo")
        @Expose
        public String logo;
        @SerializedName("banner")
        @Expose
        public Object banner;
        @SerializedName("video_banner")
        @Expose
        public String videoBanner;
        @SerializedName("background")
        @Expose
        public Object background;
        @SerializedName("profile_banner")
        @Expose
        public String profileBanner;
        @SerializedName("profile_banner_background_color")
        @Expose
        public String profileBannerBackgroundColor;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("views")
        @Expose
        public Integer views;
        @SerializedName("followers")
        @Expose
        public Integer followers;
        @SerializedName("_links")
        @Expose
        public Links links;

    }


}
