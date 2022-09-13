package com.mobileapp.weatherapp.network.interfaces

import com.mobileapp.weatherapp.model.WeatherResponse
import com.mobileapp.weatherapp.util.WeatherResult

interface Repository {
    suspend fun fetchWeatherData(city: String): WeatherResult<WeatherResponse>
}