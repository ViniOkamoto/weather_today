package com.weathertoday.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
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
    val boxSize = with(LocalDensity.current) { 360.dp.toPx() }
    val brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF54A8FF),
            Color(0xFF67E1D2)
        ),
        start = Offset(0F, boxSize),
        end = Offset(boxSize, 0F)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            modifier = modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            Box(
                //add gradient
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = brush)
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
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                fontFamily = fonts,
                text = "${data.temperatureCelsius.toInt()}°",
                fontSize = 80.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .padding(16.dp)
                .offset(y = (-100).dp, x = (-24).dp),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource(id = data.weatherType.iconRes),
                contentDescription = "Weather icon",
            )
        }
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