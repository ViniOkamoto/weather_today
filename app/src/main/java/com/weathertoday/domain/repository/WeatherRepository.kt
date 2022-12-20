package com.weathertoday.domain.repository

import com.weathertoday.core.util.Resource
import com.weathertoday.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lon: Double): Resource<WeatherInfo>
}