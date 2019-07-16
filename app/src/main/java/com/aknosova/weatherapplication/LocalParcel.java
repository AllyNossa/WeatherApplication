package com.aknosova.weatherapplication;

import java.io.Serializable;

public class LocalParcel implements Serializable {
    private String text;
    private boolean isChecked;

    public LocalParcel(String text, boolean isChecked) {
        this.text = text;
        this.isChecked = isChecked;
    }

    public String getText() {
        return text;
    }

    public boolean isChecked() {
        return isChecked;
    }
}

