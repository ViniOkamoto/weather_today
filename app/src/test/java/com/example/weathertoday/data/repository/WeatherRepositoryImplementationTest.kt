package com.plcoding.weatherapp.data.repository

import com.example.weathertoday.core.util.Resource
import com.example.weathertoday.data.mappers.toWeatherInfo
import com.example.weathertoday.data.remote.WeatherApi
import com.example.weathertoday.data.remote.WeatherDataDto
import com.example.weathertoday.data.remote.WeatherDto
import com.example.weathertoday.data.repository.WeatherRepositoryImplementation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryImplementationTest {

    @Mock
    private lateinit var api: WeatherApi

    private lateinit var repository: WeatherRepositoryImplementation


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = WeatherRepositoryImplementation(api)
    }


    @Test
    fun testGetWeatherData() = runTest{
        // Arrange
        val mock = WeatherDto(
            weatherData = WeatherDataDto(
                time = listOf("2022-12-20T00:00", "2022-12-20T01:00","2022-12-20T02:00"),
                temperatures = listOf(20.0, 20.0, 20.0),
                pressures = listOf(20.0, 20.0, 20.0),
                weatherCodes = listOf(0, 2, 3),
                windSpeeds = listOf(20.0, 20.0, 20.0),
                humidities = listOf(50.0, 50.0, 50.0)
            )
        )

        val expectedResult = Resource.Success(data = mock.toWeatherInfo())

        `when`(api.getWeatherData(lat = 0.0, lon = 0.0)).thenReturn(mock)

        // Act
        val result = repository.getWeatherData(lat = 0.0, lon = 0.0)

        // Assert
        Assert.assertEquals(expectedResult.data, result.data)
    }

    @Test
    fun testGetWeatherDataError() = runTest{
        // Arrange
        val expectedResult = Resource.Error(data = null, message = "An error occurred")

        `when`(api.getWeatherData(lat = 0.0, lon = 0.0)).thenReturn(null)

        // Act
        val result = repository.getWeatherData(lat = 0.0, lon = 0.0)

        // Assert
        Assert.assertEquals(expectedResult.data, result.data)
        Assert.assertEquals(expectedResult.message, result.message)
    }
}


