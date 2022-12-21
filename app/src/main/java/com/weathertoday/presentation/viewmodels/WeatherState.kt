package com.weathertoday.presentation.viewmodels
import com.weathertoday.domain.weather.WeatherInfo


data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
