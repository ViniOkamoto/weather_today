package com.example.weathertoday.data.repository

import com.example.weathertoday.core.util.Resource
import com.example.weathertoday.data.mappers.toWeatherInfo
import com.example.weathertoday.data.remote.WeatherApi
import com.example.weathertoday.domain.repository.WeatherRepository
import com.example.weathertoday.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(lat: Double, lon: Double): Resource<WeatherInfo> {
                return try{
                    Resource.Success(
                        data = api.getWeatherData(lat = lat, lon = lon).toWeatherInfo()
                    )
                } catch (e: Exception){
            Resource.Error("An error occurred")
        }
    }
}