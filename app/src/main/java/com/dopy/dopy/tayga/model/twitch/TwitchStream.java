
package com.dopy.dopy.tayga.model.twitch;

import android.view.View;
import android.widget.Toast;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class TwitchStream extends BroadcastModel{


    @SerializedName("viewers")
    @Expose
    public double viewers;
    @SerializedName("preview")
    @Expose
    public Preview preview;
    @SerializedName("channel")
    @Expose
    public Channel channel;
    @SerializedName("_links")
    @Expose
    public Links_ links;

    @Override
    public String showTitle() {
        return channel.status;
    }

    @Override
    public String showHostName() {
        return channel.displayName;
    }

    @Override
    public String showViewrToString() {
        int viewer=(int) viewers;
        return (viewer +"명 시청중");
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),"onItemClicked",Toast.LENGTH_LONG).show();
        /*Intent intent= new Intent(v.getContext(),GameDetailPageActivity.class);
        intent.putExtra("GameDetailPageActivity",this);
        v.getContext().startActivity(intent);*/
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
