package com.weathertoday.presentation.viewmodels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weathertoday.core.services.location.LocationTracker
import com.weathertoday.core.util.Resource
import com.weathertoday.domain.repository.WeatherRepository
import com.weathertoday.domain.weather.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            locationTracker.getCurrentLocation()?.let { location ->
                when (val result =
                    repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> state = state.copy(
                        isLoading = false,
                        weatherInfo = result.data,
                        showingWeather = result.data?.weatherDataPerDay?.values?.first()?.first()
                    )
                    is Resource.Error -> state = state.copy(
                        weatherInfo = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve your location, please try again later"
                )
            }
        }
    }
    fun selectDay(weatherData: WeatherData) {
        state = state.copy(showingWeather = weatherData)
    }
}