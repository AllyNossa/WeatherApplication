package com.aknosova.weatherapplication.rest.entities;

import com.google.gson.annotations.SerializedName;

public class WeatherModel {
    @SerializedName("id") public int id;
    @SerializedName("main") public String main;
    @SerializedName("description") public String description;
    @SerializedName("icon") public String icon;
}
