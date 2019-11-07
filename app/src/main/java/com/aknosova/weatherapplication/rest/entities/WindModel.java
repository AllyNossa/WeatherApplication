package com.aknosova.weatherapplication.rest.entities;

import com.google.gson.annotations.SerializedName;

public class WindModel {
    @SerializedName("speed") public float speed;
    @SerializedName("deg") public float deg;
}
