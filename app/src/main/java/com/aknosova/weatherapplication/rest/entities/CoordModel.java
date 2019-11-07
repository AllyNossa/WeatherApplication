package com.aknosova.weatherapplication.rest.entities;

import com.google.gson.annotations.SerializedName;

public class CoordModel {
    @SerializedName("lon") public float lon;
    @SerializedName("lat") public float lat;
}
