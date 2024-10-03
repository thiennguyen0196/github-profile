package co.thiennguyen.github_profile.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.*

@Composable
fun ComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val typography = LocalAppTypography.current
    val shapes = LocalAppShapes.current

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography,
    ) {
        MaterialTheme(
            colorScheme = colors.themeColors,
            typography = typography.themeTypography,
            shapes = shapes,
            content = content
        )
    }
}

object AppTheme {

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShapes.current

    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimensions.current
}
