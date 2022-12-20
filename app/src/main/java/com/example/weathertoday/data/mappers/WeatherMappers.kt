package com.example.weathertoday.data.mappers

import com.example.weathertoday.data.remote.WeatherDataDto
import com.example.weathertoday.data.remote.WeatherDto
import com.example.weathertoday.domain.weather.WeatherData
import com.example.weathertoday.domain.weather.WeatherInfo
import com.example.weathertoday.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val weatherData: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed{ index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index=index,
            weatherData = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                weatherType = WeatherType.fromWMO(weatherCode),
                windSpeed = windSpeed,
                pressure = pressure,
                humidity = humidity
            )
        )
    }.groupBy {
       it.index/24
    }.mapValues { entry ->
        entry.value.map { it.weatherData }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find{
        val hour = if(now.minute< 30) now.hour else now.hour+1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}