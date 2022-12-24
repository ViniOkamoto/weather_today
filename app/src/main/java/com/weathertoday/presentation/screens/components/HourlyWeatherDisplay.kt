package com.weathertoday.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.weathertoday.domain.weather.WeatherData
import com.weathertoday.shared.presentation.components.extensions.shadow
import com.weathertoday.shared.presentation.components.typography.TextMMedium
import com.weathertoday.shared.presentation.components.typography.TextXLSemiBold
import com.weathertoday.ui.theme.AppTheme
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
) {
    //create a date time format in this format 12:00am or 12:00pm
    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("h:mma")
        )
    }
    Card(
        modifier = modifier.shadow(
            color = Color(0x4D000000),
            borderRadius = 16.dp,
            blurRadius = 24.dp,
            offsetY = 4.dp,
            offsetX = 0.dp,
            spread= 0.dp,
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
    ) {
        Box(
            modifier = Modifier.background(
                color = AppTheme.colors.Blue800,
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                TextMMedium(
                    text = formattedTime,
                    color = AppTheme.colors.Gray300
                )
                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = weatherData.weatherType.iconRes),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextXLSemiBold(
                    text = "${weatherData.temperatureCelsius.toInt()}°C",
                    color = AppTheme.colors.White,
                )
            }
        }
    }
}