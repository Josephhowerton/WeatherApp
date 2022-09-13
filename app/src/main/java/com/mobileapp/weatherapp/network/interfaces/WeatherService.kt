package com.mobileapp.weatherapp.network.interfaces

import com.mobileapp.weatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/forecast")
    suspend fun fetchWeatherData(@Query("appid") apiKey: String, @Query("q") city: String): Response<WeatherResponse>

}