package com.mobileapp.weatherapp.network

import com.mobileapp.weatherapp.BuildConfig
import com.mobileapp.weatherapp.model.WeatherResponse
import com.mobileapp.weatherapp.network.interfaces.Repository
import com.mobileapp.weatherapp.network.interfaces.WeatherService
import com.mobileapp.weatherapp.util.WeatherResult
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val weatherService: WeatherService): Repository {
    override suspend fun fetchWeatherData(city: String): WeatherResult<WeatherResponse> {
        try {

            val response = weatherService.fetchWeatherData(BuildConfig.WEATHER_API_KEY, city)

            response.body()?.let {
                return WeatherResult.Success(it)
            } ?: throw Exception("Error Fetching List")

        } catch (exception: Exception){
            return WeatherResult.Error(exception)
        }
    }
}