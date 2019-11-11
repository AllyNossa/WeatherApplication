package com.aknosova.weatherapplication;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class OpenWeatherAdapter {
    private static OpenWeatherAdapter singleton = null;
    private IOpenWeather iOpenWeather;

    private OpenWeatherAdapter() {
        iOpenWeather = createAdapter();
    }

    static OpenWeatherAdapter getSingleton() {
        if (singleton == null) {
            singleton = new OpenWeatherAdapter();
        }
        return singleton;
    }

    IOpenWeather getiOpenWeather() {
        return iOpenWeather;
    }

    private IOpenWeather createAdapter() {
        OkHttpClient clientOkHttp = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor()).build();

        Retrofit adapter = new Retrofit.Builder()
                .client(clientOkHttp)
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return adapter.create(IOpenWeather.class);
    }
}
