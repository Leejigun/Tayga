package com.dopy.dopy.tayga.model.game;


import android.view.View;

import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Dopy on 2017-08-13.
 */
@Parcel
public class GameItem extends BroadcastModel {

    @SerializedName("game")
    @Expose
    public Game game;
    @SerializedName("viewers")
    @Expose
    public Integer viewers;
    @SerializedName("channels")
    @Expose
    public Integer channels;

    public String showChannels(){
        return (Integer.toString(channels)+"개의 방송이 있습니다.");
    }

    public String showGameName(){
        return (game.name);
    }

    public String showTotalViewr(){
        return (game.popularity + "명 시청중");
    }

    public String showTitle() {
        return game.name;
    }

    public String showHostName() {
        return game.name;
    }

    public String showViewrToString() {
        return showTotalViewr();
    }

    public void onClick(View v) {

    }

    @Parcel
    public static class Game {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("popularity")
        @Expose
        public Integer popularity;
        @SerializedName("_id")
        @Expose
        public Integer id;
        @SerializedName("giantbomb_id")
        @Expose
        public Integer giantbombId;
        @SerializedName("box")
        @Expose
        public Box box;
        @SerializedName("logo")
        @Expose
        public Logo logo;
        @SerializedName("localized_name")
        @Expose
        public String localizedName;
        @SerializedName("locale")
        @Expose
        public String locale;
        @Parcel
        public static class Box {

            @SerializedName("large")
            @Expose
            public String large;
            @SerializedName("medium")
            @Expose
            public String medium;
            @SerializedName("small")
            @Expose
            public String small;
            @SerializedName("template")
            @Expose
            public String template;
        }
        @Parcel
        public static class Logo {

            @SerializedName("large")
            @Expose
            public String large;
            @SerializedName("medium")
            @Expose
            public String medium;
            @SerializedName("small")
            @Expose
            public String small;
            @SerializedName("template")
            @Expose
            public String template;

        }
    }
}
