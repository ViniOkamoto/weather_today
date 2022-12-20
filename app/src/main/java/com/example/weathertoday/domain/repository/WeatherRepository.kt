package com.example.weathertoday.domain.repository

import com.example.weathertoday.core.util.Resource
import com.example.weathertoday.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lon: Double): Resource<WeatherInfo>
}