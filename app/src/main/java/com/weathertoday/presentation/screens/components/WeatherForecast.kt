package com.weathertoday.presentation.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.weathertoday.domain.weather.WeatherData

@Composable
fun WeatherForecast(
    weatherDataList: List<WeatherData>,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(weatherDataList) { weatherData ->
                HourlyWeatherDisplay(
                    weatherData = weatherData,
                )
            }
        },
    )
}