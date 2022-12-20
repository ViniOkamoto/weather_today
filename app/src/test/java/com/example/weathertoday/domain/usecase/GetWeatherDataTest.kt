package com.example.weathertoday.domain.usecase

import com.example.weathertoday.core.util.Resource
import com.example.weathertoday.domain.repository.WeatherRepository
import com.example.weathertoday.domain.weather.WeatherData
import com.example.weathertoday.domain.weather.WeatherInfo
import com.example.weathertoday.domain.weather.WeatherType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDateTime

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetWeatherDataTest {

    @Mock
    private lateinit var repository: WeatherRepository

    private lateinit var getWeatherData: GetWeatherData

    @Before
    fun setUp() {
        openMocks(this)
        getWeatherData = GetWeatherData(repository)
    }

    @Test
    fun `test get weather data success`() = runTest {
        // Set up input and expected output
        val lat = 37.5483
        val lon = 126.986
        val mock = WeatherData(
            time = LocalDateTime.now(),
            temperatureCelsius = 20.0,
            pressure = 20.0,
            windSpeed = 20.0,
            humidity = 50.0,
            weatherType = WeatherType.DenseDrizzle
        )
        //create a mock of Map<int, List<WeatherData>>
        val weatherDataMap = mapOf(0 to listOf(mock))
        val expectedWeatherInfo = WeatherInfo(
            weatherDataMap,
            WeatherData(
                time = LocalDateTime.now(),
                temperatureCelsius = 20.0,
                pressure = 20.0,
                windSpeed = 20.0,
                humidity = 50.0,
                weatherType = WeatherType.DenseDrizzle

            )
        )
        Mockito.`when`(repository.getWeatherData(lat, lon))
            .thenReturn(Resource.Success(expectedWeatherInfo))

        // Invoke the method under test
        val result = getWeatherData.invoke(lat, lon)

        // Verify the result
        Assert.assertTrue(result is Resource.Success)
        val weatherInfo = (result as Resource.Success).data
        Assert.assertEquals(expectedWeatherInfo, weatherInfo)
    }

    @Test
    fun `test get weather data error`() = runTest {
        // Set up input and expected output
        val lat = 37.5483
        val lon = 126.986
        val expectedErrorMessage = "Error fetching weather data"
        Mockito.`when`(repository.getWeatherData(lat, lon))
            .thenReturn(Resource.Error(expectedErrorMessage))

        // Invoke the method under test
        val result = getWeatherData.invoke(lat, lon)

        // Verify the result
        Assert.assertTrue(result is Resource.Error)
        val errorMessage = (result as Resource.Error).message
        Assert.assertEquals(expectedErrorMessage, errorMessage)

    }
}