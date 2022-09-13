package com.mobileapp.weatherapp.util

sealed class WeatherResult<out R> {
    data class Success<out T>(val data: T): WeatherResult<T>()
    data class Error(val exception: Exception): WeatherResult<Nothing>()
}