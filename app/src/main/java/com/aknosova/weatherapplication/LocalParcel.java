package com.aknosova.weatherapplication;

import java.io.Serializable;

public class LocalParcel implements Serializable {
    private String text;
    private boolean isChecked;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}

