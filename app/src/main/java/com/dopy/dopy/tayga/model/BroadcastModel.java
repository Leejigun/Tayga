package com.dopy.dopy.tayga.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.dopy.dopy.tayga.ui.GameDetailPageActivity;
import com.dopy.dopy.tayga.ui.YoutubeSampleActivity;

import java.net.URL;

/**
 * Created by Dopy on 2017-08-08.
 */
public class BroadcastModel implements Parcelable {
    public static final String HEARTHSTONE = "hearthstone";
    public static final String BATTLE_GROUND = "battleground";
    public static final String OVERWATCH = "overwatch";

    String title;
    String hostName;
    int numberOfViewer;
    URL snapshot;
    String tag;
    int isfavorites = 0;
    public BroadcastModel(String title, String hostName, int numberOfViewr, URL snapshot, String tag, int isfavorites) {
        this.title = title;
        this.hostName = hostName;
        this.numberOfViewer = numberOfViewr;
        this.snapshot = snapshot;
        this.tag = tag;
        this.isfavorites = isfavorites;
    }

    protected BroadcastModel(Parcel in) {
        title = in.readString();
        hostName = in.readString();
        numberOfViewer = in.readInt();
        tag = in.readString();
        isfavorites = in.readInt();
    }

    public static final Creator<BroadcastModel> CREATOR = new Creator<BroadcastModel>() {
        @Override
        public BroadcastModel createFromParcel(Parcel in) {
            return new BroadcastModel(in);
        }

        @Override
        public BroadcastModel[] newArray(int size) {
            return new BroadcastModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getNumberOfViewer() {
        return numberOfViewer;
    }

    public void setNumberOfViewer(int numberOfViewr) {
        this.numberOfViewer = numberOfViewr;
    }

    public URL getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(URL snapshot) {
        this.snapshot = snapshot;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getIsfavorites() {
        return isfavorites;
    }

    public void setIsfavorites(int isfavorites) {
        this.isfavorites = isfavorites;
    }

    public String getnumberOfViewrToStringNotNull(){
        if(numberOfViewer == 0) return "";
        else return Integer.toString(numberOfViewer)+ "명 시청중";
    }
    //    Item Click listener
    public void onClickItem(View v){
        Intent intent = new Intent(v.getContext(),GameDetailPageActivity.class);
        intent.putExtra(this.getClass().toString(),this);
        v.getContext().startActivity(intent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(hostName);
        parcel.writeInt(numberOfViewer);
        parcel.writeString(tag);
        parcel.writeInt(isfavorites);
    }
}