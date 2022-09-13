package com.mobileapp.weatherapp.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobileapp.weatherapp.model.WeatherData
import com.mobileapp.weatherapp.util.TempConverter
import com.mobileapp.weatherapp.view.components.WeatherAppBar
import com.mobileapp.weatherapp.viewmodel.MainViewModel

@Composable
private fun WeatherItem(data: WeatherData, showDivider:Boolean, modifier: Modifier){
    val temp = TempConverter.convertTemperature(data.main.temp)
    val description = data.weather[0].main

    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text(text = description, fontWeight = FontWeight.Medium, modifier = Modifier.padding(25.dp))
            Text(text = "Temp: $temp", fontWeight = FontWeight.Medium, modifier = Modifier.padding(25.dp))
        }

        if(showDivider){
            Divider(
                color = Color.Black,
                thickness = 1.dp
            )
        }
    }
}

@Composable
fun WeatherScreen(viewModel: MainViewModel, navController: NavController) {
    Scaffold(
        topBar = { WeatherAppBar(viewModel.topAppBarTitle.value, navController ) }
    ){
        val weatherData = remember {
            viewModel.weatherDataList
        }

        val lastItem = weatherData.size - 1

        LazyColumn{
            items(weatherData.size){ index ->
                WeatherItem(
                    data = weatherData[index],
                    showDivider = index != lastItem,
                    modifier = Modifier.clickable {
                        viewModel.navigateToWeatherDetails(index)
                    }
                )
            }
        }
    }
}