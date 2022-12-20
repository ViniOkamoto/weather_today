package com.example.weathertoday.domain.usecase

import com.example.weathertoday.core.util.Resource
import com.example.weathertoday.domain.repository.WeatherRepository
import com.example.weathertoday.domain.weather.WeatherInfo
import javax.inject.Inject

class GetWeatherData @Inject constructor(private val repository: WeatherRepository) {
    suspend fun invoke(lat: Double, lon: Double): Resource<WeatherInfo> = repository.getWeatherData(lat, lon)
}