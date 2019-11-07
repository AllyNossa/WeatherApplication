package com.aknosova.weatherapplication;

import com.aknosova.weatherapplication.rest.entities.WeatherModelRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeather {
    @GET("data/2.5/weather")
    Call<WeatherModelRequest> loadWeather(@Query("q") String city,
                                          @Query("appid") String keyApi,
                                          @Query("units") String units);
}
