package com.aknosova.weatherapplication;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class MainService extends IntentService {
    private Intent responseIntent;
    private String temperatureValue;

    public MainService() {
        super("MainService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String cityValue = intent.getStringExtra(DataDisplayFragment.CITYVALUE);

            switch (cityValue) {
                case "Moscow":
                    temperatureValue = "+26";
                    break;
                case "Omsk":
                    temperatureValue = "-5";
                    break;
                case "Samara":
                    temperatureValue = "-15";
                default:
                    temperatureValue = "+30";
            }

            responseIntent = new Intent(DataDisplayFragment.BROADCAST_ACTION);
            responseIntent.putExtra(DataDisplayFragment.CITYVALUE, temperatureValue);

            sendBroadcast(responseIntent);
        }
    }
}
