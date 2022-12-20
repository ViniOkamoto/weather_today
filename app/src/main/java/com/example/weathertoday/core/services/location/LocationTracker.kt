package com.example.weathertoday.core.services.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}