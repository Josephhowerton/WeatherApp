package com.mobileapp.weatherapp.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobileapp.weatherapp.util.Screen
import com.mobileapp.weatherapp.view.screens.SearchCityScreen
import com.mobileapp.weatherapp.view.screens.WeatherDetailsScreen
import com.mobileapp.weatherapp.view.screens.WeatherScreen
import com.mobileapp.weatherapp.viewmodel.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NavGraph(mainViewModel: MainViewModel,
             navigator: Navigator,
             navController: NavHostController = rememberNavController()) {

    LaunchedEffect(key1 = "navigation"){
        navigator.sharedFlow.onEach {
            navController.navigate(it)
        }.launchIn(this)
    }

    NavHost(navController = navController, startDestination = Screen.SEARCH_CITY_SCREEN) {

        composable(Screen.SEARCH_CITY_SCREEN) { SearchCityScreen(mainViewModel) }
        composable(Screen.WEATHER_DETAILS_SCREEN) { WeatherDetailsScreen(mainViewModel, navController) }
        composable(Screen.WEATHER_SCREEN) { WeatherScreen(mainViewModel, navController) }
    }
}