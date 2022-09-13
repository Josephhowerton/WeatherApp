package com.mobileapp.weatherapp.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobileapp.weatherapp.util.TempConverter
import com.mobileapp.weatherapp.view.components.WeatherAppBar
import com.mobileapp.weatherapp.viewmodel.MainViewModel

@Composable
fun WeatherDetailsScreen(viewModel: MainViewModel, navController: NavController) {
    Scaffold(
        topBar = { WeatherAppBar(viewModel.topAppBarTitle.value, navController) }
    ) {
        Column(Modifier.fillMaxSize()) {

            val weatherDetails = viewModel.getSelectedWeatherDetails()
            val temperature = TempConverter.convertTemperature(weatherDetails.main.temp).toString()
            val feelsLikeTemperature = TempConverter.convertTemperature(weatherDetails.main.feels_like)
            val description = weatherDetails.weather[0].main
            val detailedDescription = weatherDetails.weather[0].description

            Text(text = temperature,
                fontSize = 75.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            )

            Text(text = "Feels Like: $feelsLikeTemperature",
                fontSize = 24.sp,
                textAlign = TextAlign.End,
                fontWeight = FontWeight(475),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, end = 25.dp)
            )
            Text(text = description,
                fontSize = 36.sp,
                fontWeight = FontWeight(490),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 25.dp)
            )

            Text(text = detailedDescription,
                fontSize = 24.sp,
                fontWeight = FontWeight(475),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, top = 10.dp)
            )
        }
    }
}