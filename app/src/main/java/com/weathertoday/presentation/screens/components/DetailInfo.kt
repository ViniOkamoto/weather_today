package com.weathertoday.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.weathertoday.shared.presentation.components.typography.TextLMedium
import com.weathertoday.shared.presentation.components.typography.TextMMedium
import com.weathertoday.shared.presentation.components.typography.TextXLSemiBold
import com.weathertoday.ui.theme.AppTheme


@Composable
fun DetailInfo(icon: ImageVector, title: String, value: String, complementValue: String? = null) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AppTheme.colors.Blue600,
            modifier = Modifier.size(24.dp)
        )
        TextLMedium(text = title, color = AppTheme.colors.Gray300)
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextXLSemiBold(text = value, color = AppTheme.colors.White)
            complementValue?.let { complement ->
                TextMMedium(text = complement, color = AppTheme.colors.White)
            }
        }
    }
}