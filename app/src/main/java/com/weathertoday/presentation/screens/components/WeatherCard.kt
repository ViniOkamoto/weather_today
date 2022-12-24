package com.weathertoday.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weathertoday.domain.weather.WeatherData
import com.weathertoday.domain.weather.WeatherType
import com.weathertoday.shared.presentation.components.typography.TextSRegular
import com.weathertoday.shared.presentation.components.typography.TitleLBold
import com.weathertoday.ui.theme.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    data: WeatherData,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            backgroundColor = Color.Transparent,
            modifier = Modifier
                .height(200.dp)
                .padding(horizontal = 16.dp),
            elevation = 0.dp,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    TitleLBold(
                        text = data.weatherType.weatherDesc,
                        color = Color.White
                    )
                    TextSRegular(
                        text = "Last update: Today ${
                            data.time.format(
                                DateTimeFormatter.ofPattern("HH:mm")
                            )
                        }",
                        color = AppTheme.colors.Gray100,
                    )
                }
            }
        }
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = (-24).dp),
        ) {
            Text(
                fontFamily = fonts,
                text = "${data.temperatureCelsius.toInt()}°",
                fontSize = 80.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Image(
            modifier = Modifier
                .size(160.dp)
                .offset(y = (-70).dp),
            painter = painterResource(id = data.weatherType.iconRes),

            contentDescription = "Weather icon",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyCardPreview() {
    WeatherCard(
        data = WeatherData(
            time = LocalDateTime.now(),
            temperatureCelsius = 20.0,
            weatherType = WeatherType.ClearSky,
            windSpeed = 10.0,
            humidity = 20.0,
            pressure = 1000.0,
        )
    )
}