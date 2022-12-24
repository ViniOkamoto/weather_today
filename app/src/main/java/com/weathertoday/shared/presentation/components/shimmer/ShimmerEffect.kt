package com.weathertoday.shared.presentation.components.shimmer
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.weathertoday.ui.theme.AppTheme

@Composable
fun AnimatedShimmer(child: @Composable (brush: Brush) -> Unit) {
    val shimmerColors = listOf(
        AppTheme.colors.Blue800,
        AppTheme.colors.Blue900,
        AppTheme.colors.Blue800,
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    child(brush)
}


