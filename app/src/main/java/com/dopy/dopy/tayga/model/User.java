package com.dopy.dopy.tayga.model;

import android.net.Uri;

import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

/**
 * Created by Dopy on 2017-08-20.
 */
@IgnoreExtraProperties
public class User {
    String userID;
    String displayname;
    String email;
    String potoUri;
    HashMap<String,TwitchStream> favoritesMap;
    /*Uri로 하니까 에러가 발생 검색결과 Uri를 firebase가 getter setter지원하지 않는다고 함*/

    public User() {
    }

    public User(String userID, String displayname, String email, String potoUri) {
        this.userID = userID;
        this.displayname = displayname;
        this.email = email;
        this.potoUri = potoUri;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPotoUri() {
        return potoUri;
    }

    public void setPotoUri(String potoUri) {
        this.potoUri = potoUri;
    }

    public HashMap<String, TwitchStream> getFavoritesMap() {
        return favoritesMap;
    }

    public void setFavoritesMap(HashMap<String, TwitchStream> favoritesMap) {
        this.favoritesMap = favoritesMap;
    }
}
