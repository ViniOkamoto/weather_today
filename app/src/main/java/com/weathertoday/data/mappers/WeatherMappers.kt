package com.weathertoday.data.mappers

import com.weathertoday.data.remote.WeatherDataDto
import com.weathertoday.data.remote.WeatherDto
import com.weathertoday.domain.weather.WeatherData
import com.weathertoday.domain.weather.WeatherInfo
import com.weathertoday.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val weatherData: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
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
        it.index / 24
    }.mapValues { entry ->
        entry.value.map { it.weatherData }
    }
}

private fun filterForecast(weatherDataMap: Map<Int, List<WeatherData>>): List<WeatherData> {
    var forecast: List<WeatherData> = emptyList()
    val now = LocalDateTime.now()
    weatherDataMap.forEach { (i, list) ->
        if(i > 3) {
            return@forEach
        }
        list.forEach { weatherData ->
            if (weatherData.time.isAfter(LocalDateTime.now()) || isWeatherDataCurrent(
                    weatherData,
                    now
                )
            ) {
                forecast = forecast.plus(weatherData)
            }
        }
    }
    return forecast
}

private fun isWeatherDataCurrent(weatherData: WeatherData, now: LocalDateTime): Boolean {
    val hour = if (now.minute < 30) now.hour else now.hour + 1
    return weatherData.time.hour == hour
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        isWeatherDataCurrent(it, now)
    }!!
    val weatherForecast = filterForecast(weatherDataMap)
    println("weatherForecast: ${weatherForecast.size}")
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData,
        weatherForecast
    )
}