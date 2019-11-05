package com.aknosova.weatherapplication;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class LocalParcel implements Serializable {
    private String text;

    public LocalParcel(@NonNull String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

