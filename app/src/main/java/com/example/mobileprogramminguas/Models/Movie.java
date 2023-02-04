package com.example.mobileprogramminguas.Models;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("fullTitle")
    public String fullTitle;

    @SerializedName("releaseState")
    public String releaseState;

    @SerializedName("image")
    public String image;

    @SerializedName("runtimeStr")
    public String runtimeStr;

    @SerializedName("plot")
    public String plot;

    @SerializedName("contentRating")
    public String contentRating;

    @SerializedName("genres")
    public String genres;

    public Movie(String fullTitle, String releaseState, String image, String runtimeStr, String plot, String contentRating, String genres) {
        this.fullTitle = fullTitle;
        this.releaseState = releaseState;
        this.image = image;
        this.runtimeStr = runtimeStr;
        this.plot = plot;
        this.contentRating = contentRating;
        this.genres = genres;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getReleaseState() {
        return releaseState;
    }

    public void setReleaseState(String releaseState) {
        this.releaseState = releaseState;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRuntimeStr() {
        return runtimeStr;
    }

    public void setRuntimeStr(String runtimeStr) {
        this.runtimeStr = runtimeStr;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
