package com.laquysoft.recipepuppyapp.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by joaobiriba on 08/09/2017.
 */

public class Recipe  implements Serializable {

    @Expose
    private String thumbnail;
    @Expose
    private String title;
    @Expose
    private String href;

    public String getThumbnail() {

        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {

        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
