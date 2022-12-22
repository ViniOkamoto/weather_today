@file:OptIn(ExperimentalMaterialApi::class)

package com.weathertoday.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weathertoday.domain.weather.WeatherData
import com.weathertoday.domain.weather.WeatherType
import com.weathertoday.shared.presentation.components.typography.TextLMedium
import com.weathertoday.shared.presentation.components.typography.TextMMedium
import com.weathertoday.shared.presentation.components.typography.TitleLBold
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.weathertoday.R
import com.weathertoday.shared.presentation.components.extensions.conditional
import com.weathertoday.shared.presentation.components.extensions.shadow
import com.weathertoday.ui.theme.AppTheme

@Composable
fun WeatherDaysList(
    modifier: Modifier = Modifier,
    weatherDataPerDay: Map<Int, List<WeatherData>>,
    onDaySelected: (WeatherData) -> Unit,
    selectedDay: WeatherData,
) {
    LazyRow(
        modifier =modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        content = {

            items(weatherDataPerDay.size) { index ->
                WeatherDayCard(
                    weatherDataPerDay[index]!!.first(),
                    selected = selectedDay.time.dayOfYear ==
                            weatherDataPerDay[index]!!.first().time.dayOfYear,
                    onDaySelected = onDaySelected
                )
            }
        },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun WeatherDayCardPreview() {
    WeatherDayCard(
        WeatherData(
            time = LocalDateTime.now(),
            temperatureCelsius = 20.0,
            weatherType = WeatherType.ClearSky,
            windSpeed = 10.0,
            humidity = 20.0,
            pressure = 1000.0,
        )
    )
}

@Composable
private fun WeatherDayCard(
    weatherData: WeatherData,
    selected: Boolean = false,
    onDaySelected: (WeatherData) -> Unit = {}
) {
    weatherData.weatherType.iconRes

    Card(
        modifier = Modifier
            .conditional(
                selected, {
                    //TODO: Check how to pass Apptheme through this modifier.
                    shadow(
                        Color(0xFFCBD6E7),
                        borderRadius = 100.dp,
                        offsetX = 0.dp,
                        offsetY = 0.dp,
                        blurRadius = 80.dp,
                        spread = 16.dp
                    )
                }, {
                    this
                }
            ),
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(100.dp),
        elevation = 0.dp,
        onClick = { onDaySelected(weatherData) }
    ) {
        Box(
            modifier = Modifier
                .conditional(selected, {
                    background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF66E0D1),
                                Color(0xFF579FF1)
                            )
                        )
                    )
                    //TODO: Check how to import apptheme colors inside of this implementation

                }, {
                    background(Color.Transparent)
                })
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextLMedium(
                    text = weatherData.time.format(DateTimeFormatter.ofPattern("EEE")),
                    color = if (selected) AppTheme.colors.White else AppTheme.colors.Gray800
                )
                TextMMedium(
                    text = weatherData.time.format(DateTimeFormatter.ofPattern("dd/MM")),
                    color = if (selected) AppTheme.colors.Blue100 else AppTheme.colors.Gray400
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = weatherData.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                TitleLBold(
                    text = "${weatherData.temperatureCelsius.toInt()}°",
                    color = if (selected) AppTheme.colors.White else AppTheme.colors.Gray900
                )
                Spacer(modifier = Modifier.height(16.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_cloud_rain),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = if (selected) AppTheme.colors.Blue100 else AppTheme.colors.Gray200
                )
                TextMMedium(
                    text = "${weatherData.humidity.toInt()}%",
                    color = if (selected) AppTheme.colors.Blue100 else AppTheme.colors.Gray200
                )
            }
        }
    }


}