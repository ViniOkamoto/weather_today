package com.weathertoday.shared.presentation.components.shimmer.skeletons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.weathertoday.shared.presentation.components.shimmer.AnimatedShimmer

@Composable
fun WeatherCardSkeleton() {
    AnimatedShimmer(child = {Skeleton(it)})
}

@Composable
private fun Skeleton(brush: Brush) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal= 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(brush)
                .height(200.dp)

        )
    }
}