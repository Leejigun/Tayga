package com.dopy.dopy.tayga.model;

import android.net.Uri;

/**
 * Created by Dopy on 2017-08-18.
 */

public class User {
    String name;
    String email;
    Uri photoUri;
    String uid;

    public User(String name, String email, Uri photoUri, String uid) {
        this.name = name;
        this.email = email;
        this.photoUri = photoUri;
        this.uid = uid;
    }
}
