package com.aknosova.weatherapplication.rest.entities;

import com.google.gson.annotations.SerializedName;

public class WeatherModelRequest {
    @SerializedName("coord") public CoordModel coords;
    @SerializedName("weather") public WeatherModel weather[];
    @SerializedName("base") public String base;
    @SerializedName("main") public MainModel main;
    @SerializedName("visibility") public int visibility;
    @SerializedName("wind") public WindModel wind;
    @SerializedName("clouds") public  CloudsModel clouds;
    @SerializedName("dt") public long dt;
    @SerializedName("sys") public  SysModel sys;
    @SerializedName("id") public  long id;
    @SerializedName("timezone") public int timezone;
    @SerializedName("name") public String name;
    @SerializedName("cod") public int cod;
}
