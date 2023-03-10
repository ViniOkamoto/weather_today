package com.weathertoday.domain.weather

data class WeatherInfo (
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData,
    val weatherForecast: List<WeatherData>
    )