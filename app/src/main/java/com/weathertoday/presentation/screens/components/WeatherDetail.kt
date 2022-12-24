package com.weathertoday.presentation.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weathertoday.ui.theme.AppTheme
import com.weathertoday.R
import com.weathertoday.shared.presentation.components.typography.*

@ExperimentalMaterialApi
@Composable
fun WeatherDetail(modifier: Modifier = Modifier, onRefresh: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                TitleLBold(text = "Today", color = AppTheme.colors.White)
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false){
                    IconButton(modifier = Modifier.size(24.dp),
                        onClick = {
                            onRefresh()
                        }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_clock_wise),
                            contentDescription = null,
                            tint = AppTheme.colors.Gray400,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                DetailInfo(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                    title = "Haze",
                    value = "9",
                    complementValue = "km/h"

                )
                DetailInfo(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                    title = "Drop",
                    value = "0.9"
                )
                DetailInfo(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_cloud_rain),
                    title = "Cloud",
                    value = "80%"
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                DetailInfo(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_sun_horizon),
                    title = "Sun rise",
                    value = "5:00am",
                )
                DetailInfo(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_thermometer),
                    title = "Temp",
                    value = "26.5°C"
                )
                DetailInfo(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_sun_horizon),
                    title = "Sun rise",
                    value = "5:00pm",
                )
            }
        }
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun WeatherDetailPreview() {
    WeatherDetail(
        onRefresh = {}
    )
}
