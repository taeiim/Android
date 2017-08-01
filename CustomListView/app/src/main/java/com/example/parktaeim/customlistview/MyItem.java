package com.example.parktaeim.customlistview;

import android.graphics.drawable.Drawable;

/**
 * Created by parktaeim on 2017. 8. 1..
 */

public class MyItem {

    private Drawable profile;
    private String name;
    private String comment;

    public Drawable getProfile() {
        return profile;
    }

    public void setProfile(Drawable profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
