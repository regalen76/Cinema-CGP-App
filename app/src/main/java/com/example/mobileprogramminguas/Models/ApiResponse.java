package com.example.mobileprogramminguas.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    @SerializedName("items")
    List<Movie> items = null;

    public ApiResponse(List<Movie> items) {
        this.items = items;
    }

    public List<Movie> getItems() {
        return items;
    }

    public void setItems(List<Movie> items) {
        this.items = items;
    }
}
