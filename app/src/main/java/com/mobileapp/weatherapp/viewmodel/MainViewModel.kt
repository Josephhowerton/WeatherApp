package com.mobileapp.weatherapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileapp.weatherapp.model.WeatherData
import com.mobileapp.weatherapp.network.interfaces.Repository
import com.mobileapp.weatherapp.util.Screen
import com.mobileapp.weatherapp.util.WeatherResult
import com.mobileapp.weatherapp.view.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val navigator: Navigator
) : ViewModel() {

    val weatherDataList = mutableListOf<WeatherData>()
    private val selectedIndex = mutableStateOf(0)

    val topAppBarTitle = mutableStateOf("Weather App")
    val shouldShowErrorDialog = mutableStateOf(false)
    val shouldShowProgressBar = mutableStateOf(false)

    fun fetchWeatherData(city: String) {
        shouldShowProgressBar.value = true
        viewModelScope.launch(Dispatchers.Main) {
            when (val weatherResult = async { repository.fetchWeatherData(city) }.await()) {
                is WeatherResult.Success -> {
                    topAppBarTitle.value = weatherResult.data.city.name
                    weatherDataList.clear()
                    weatherDataList.addAll(weatherResult.data.list)
                    navigator.navigateTo(Screen.WEATHER_SCREEN)
                    shouldShowProgressBar.value = false
                }

                is WeatherResult.Error -> {
                    shouldShowErrorDialog.value = true
                    shouldShowProgressBar.value = false
                }
            }
        }


    }

    fun getSelectedWeatherDetails() = weatherDataList[selectedIndex.value]

    fun navigateToWeatherDetails(index: Int) {
        selectedIndex.value = index
        navigator.navigateTo(Screen.WEATHER_DETAILS_SCREEN)
    }
}