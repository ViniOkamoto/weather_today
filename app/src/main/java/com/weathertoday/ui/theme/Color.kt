package com.weathertoday.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colors(
    val Blue600 : Color = Color(0xFF2F90FF),
    val Blue500 : Color = Color(0xFF00BCFF),
    val Blue400 : Color = Color(0xFF5AB9F3),
    val Blue300 : Color = Color(0xFF7EB9C5),
    val Blue200 : Color = Color(0xFFB2D7DE),
    val Blue100 : Color = Color(0xFFD0F3FF),
    val Gray900 : Color = Color(0xFF29292C),
    val Gray800 : Color = Color(0xFF333333),
    val Gray400 : Color = Color(0xFFA0A7BA),
    val Gray300 : Color = Color(0xFFCDD2DE),
    val Gray200 : Color = Color(0xFFCBD6E7),
    val Gray100 : Color = Color(0xFFE5EBF5),
    val White : Color = Color(0xFFFFFFFF),
    val Yellow500 : Color = Color(0xFFFFD439),
    val Green500 : Color = Color(0xFF37E4AA),
    val Green400 : Color = Color(0xFF68E2D3),
    val Red500 : Color = Color(0xFFFD7778),
)
val LocalSimpleColors = staticCompositionLocalOf { Colors() }
