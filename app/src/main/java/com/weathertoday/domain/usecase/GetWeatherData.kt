package com.weathertoday.domain.usecase

import com.weathertoday.core.util.Resource
import com.weathertoday.domain.repository.WeatherRepository
import com.weathertoday.domain.weather.WeatherInfo
import javax.inject.Inject

class GetWeatherData @Inject constructor(private val repository: WeatherRepository) {
    suspend fun invoke(lat: Double, lon: Double): Resource<WeatherInfo> = repository.getWeatherData(lat, lon)
}