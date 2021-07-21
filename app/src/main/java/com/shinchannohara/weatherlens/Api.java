package com.shinchannohara.weatherlens;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("weather")
    Call<weatherdata> getWeather(
            @Query("q") String q,
            @Query("appid") String appid
    );

    @GET("weather")
    Call<weatherdata> getWeatherNewCity(
            @Query("lat") String latitude,
            @Query("lon") String longitude,
            @Query("appid") String appid
    );

}
