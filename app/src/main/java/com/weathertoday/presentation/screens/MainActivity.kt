package com.weathertoday.presentation.screens

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.weathertoday.presentation.screens.components.WeatherCard
import com.weathertoday.presentation.screens.components.WeatherDetail
import com.weathertoday.presentation.viewmodels.WeatherViewModel
import com.weathertoday.shared.presentation.components.shimmer.skeletons.WeatherCardSkeleton
import com.weathertoday.shared.presentation.components.shimmer.skeletons.WeatherDetailSkeleton
import com.weathertoday.ui.theme.WeatherTodayTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()

        setContent {
            WeatherScreen(viewModel)
        }
    }

    private fun requestPermission() {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
    }


}

@ExperimentalMaterialApi
@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val scrollState = rememberScrollState()
    WeatherTodayTheme() {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                if (viewModel.state.isLoading && viewModel.state.weatherInfo == null) {
                    WeatherCardSkeleton()
                    WeatherDetailSkeleton()
                }
                viewModel.state.weatherInfo?.currentWeatherData?.let {
                    WeatherCard(data = it)
                    WeatherDetail(onRefresh = { viewModel.loadWeatherInfo() })
                }

            }
            viewModel.state.error?.let { error ->
                Text(
                    text = error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}



