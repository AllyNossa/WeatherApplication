package com.aknosova.weatherapplication;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class LocalParcel implements Serializable {
    private String text;
    private boolean isHumidityChecked;
    private boolean isPressureChecked;

    public LocalParcel(@NonNull String text, boolean isHumidityChecked, boolean isPressureChecked) {
        this.text = text;
        this.isHumidityChecked = isHumidityChecked;
        this.isPressureChecked = isPressureChecked;

    }

    public boolean isPressureChecked() {
        return isPressureChecked;
    }

    public String getText() {
        return text;
    }

    public boolean isHumidityChecked() {
        return isHumidityChecked;
    }
}

