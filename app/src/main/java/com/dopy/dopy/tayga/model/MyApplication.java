package com.dopy.dopy.tayga.model;

import android.app.Application;

/**
 * Created by Dopy on 2017-08-21.
 */

public class MyApplication extends Application {
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
