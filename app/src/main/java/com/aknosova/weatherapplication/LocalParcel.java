package com.aknosova.weatherapplication;

import java.io.Serializable;

public class LocalParcel implements Serializable {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
