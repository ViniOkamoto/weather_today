package com.weathertoday.shared.presentation.components.typography

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.weathertoday.ui.theme.AppTheme
import com.weathertoday.ui.theme.LocalSimpleTypography


@Composable
fun TextLSemiBold(
    text: String,
    color: Color = AppTheme.colors.Gray900,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = LocalSimpleTypography.current.TextLSemiBold,
        modifier = modifier,
        color = color
    )
}

