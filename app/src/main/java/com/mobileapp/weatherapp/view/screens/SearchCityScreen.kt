package com.mobileapp.weatherapp.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mobileapp.weatherapp.viewmodel.MainViewModel

@Composable
fun SearchCityScreen(viewModel: MainViewModel) {
    Scaffold {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment= Alignment.CenterHorizontally,
        ) {
            var city by remember {
                mutableStateOf("")
            }

            val showProgressBar = remember {
                viewModel.shouldShowProgressBar
            }

            val showErrorDialog = remember {
                viewModel.shouldShowErrorDialog
            }

            if(showProgressBar.value){
                CircularProgressIndicator()
            }

            if(showErrorDialog.value){
                AlertDialog(
                    onDismissRequest = { viewModel.shouldShowErrorDialog.value = false },
                    title = { Text(text = "Uh Oh!") },
                    text = { Text("We could not find you city!") },
                    buttons = {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { viewModel.shouldShowErrorDialog.value = false }
                            ) {
                                Text("Dismiss")
                            }
                        }
                    }
                )
            }


            TextField(value = city,
                colors = TextFieldDefaults.textFieldColors( backgroundColor = Color.Transparent),
                label = { Text(text = "City Name", Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
                modifier = Modifier.padding(start = 60.dp, end = 60.dp, bottom = 25.dp),
                onValueChange = {
                    city = it
                }
            )

            OutlinedButton(border = BorderStroke(1.dp, Color.Black),
                enabled = city.isNotEmpty(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors( backgroundColor = Color.Transparent ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 100.dp, end = 100.dp),
                onClick = { viewModel.fetchWeatherData(city) }
            ) {
                Text(text = "Lookup", color = Color.Black, modifier = Modifier.padding(top = 3.dp, bottom = 3.dp))
            }
        }
    }
}