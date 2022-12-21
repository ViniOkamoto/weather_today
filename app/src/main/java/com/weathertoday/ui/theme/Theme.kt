package com.weathertoday.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val DarkColorPalette = darkColors(
    primary = Colors().Blue500,
    primaryVariant = Colors().Blue600,
    secondary = Colors().Green400
)

private val LightColorPalette = lightColors(
    primary = Colors().Blue500,
    primaryVariant = Colors().Blue600,
    secondary = Colors().Green400,
    background = Colors().White,

    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

object AppTheme {

    val typography
        @Composable
        @ReadOnlyComposable
        get() = LocalSimpleTypography.current

    val colors
        @Composable
        @ReadOnlyComposable
        get() = LocalSimpleColors.current
}

@Composable
fun WeatherTodayTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    CompositionLocalProvider(
        LocalSimpleTypography provides AppTypography(),
    ) {
    MaterialTheme(
            colors = colors,
            shapes = Shapes,
            content = content
        )
    }
}
