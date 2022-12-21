package com.weathertoday.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.weathertoday.R

val fonts = FontFamily(
    Font(R.font.inter_bold, weight = FontWeight.Bold),
    Font(R.font.inter_semibold, weight = FontWeight.SemiBold),
    Font(R.font.inter_medium, weight = FontWeight.Medium),
    Font(R.font.inter_regular, weight = FontWeight.Normal),
)

data class AppTypography(
    val FontFamily: FontFamily = FontFamily(
        Font(R.font.inter_bold, weight = FontWeight.Bold),
        Font(R.font.inter_semibold, weight = FontWeight.SemiBold),
        Font(R.font.inter_medium, weight = FontWeight.Medium),
        Font(R.font.inter_regular, weight = FontWeight.Normal),
    ),
    val TitleL: TextStyle = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        lineHeight = 28.8.sp,
        fontSize = 24.sp
    ),
    val TextXLMedium: TextStyle = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        fontSize = 20.sp
    ),
    val TextXLSemiBold : TextStyle = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        fontSize = 20.sp
    ),
    val TextLMedium : TextStyle = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        lineHeight = 21.6.sp,
        fontSize = 18.sp
    ),
    val TextMMedium: TextStyle = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        fontSize = 16.sp
    ),
    val TextMSemiBold: TextStyle = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        fontSize = 16.sp
    ),
    val TextSRegular: TextStyle = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.8.sp,
        fontSize = 14.sp
    ),
)

val LocalSimpleTypography = staticCompositionLocalOf { AppTypography() }
