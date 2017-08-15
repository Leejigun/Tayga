package com.dopy.dopy.tayga.model.youtube;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dopy on 2017-08-15.
 */

public class Thumbnails {
    @SerializedName("default")
    @Expose
    public Default _default;
    @SerializedName("medium")
    @Expose
    public Medium medium;
    @SerializedName("high")
    @Expose
    public High high;

    public class Default {

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

    public class Medium {

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

    public class High {

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
