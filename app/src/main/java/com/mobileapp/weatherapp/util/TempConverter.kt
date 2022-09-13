package com.mobileapp.weatherapp.util

import kotlin.math.roundToInt

object TempConverter {
    fun convertTemperature(temp: Double): Int = ((fahrenheitToKelvin(temp) * 1000.0) /1000.0).roundToInt()
    private fun fahrenheitToKelvin(temp: Double): Double = (temp - 273.5f) * (9.0f/5.0f) + 32.0
}