package com.aknosova.weatherapplication;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DataLoadWeatherOkHttp {
    private static final String OPEN_WEATHER_API_KEY = "f3f2763fe63803beef4851d6365c83bc";
    private static final String OPEN_WEATHER_API_URL =
            "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private static final String KEY = "x-api-key";
    private static final String RESPONSE = "cod";
    private static final int ALL_GOOD = 200;

    private static String request(String city) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        Request request = new Request.Builder()
                .addHeader(KEY, OPEN_WEATHER_API_KEY)
                .url(String.format(OPEN_WEATHER_API_URL, city))
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Log.e("OkHttp", e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    static JSONObject getJSONData(String city) {
        try {
            String rawData = request(city);

            if (rawData == null) {
                return null;
            }

            JSONObject jsonObject = new JSONObject(rawData);
            if (jsonObject.getInt(RESPONSE) != ALL_GOOD) {
                return null;
            } else {
                return jsonObject;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
    }
}
