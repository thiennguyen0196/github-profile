package co.thiennguyen.github_profile.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AppDimensions {
    // Corner radius values
    val cornerSmall = 8.dp
    val cornerMedium = 16.dp

    // Divider values
    val dividerThickness = 1.dp

    // Font size
    val fontSizeSmall = 10.sp
    val fontSizeMedium = 12.sp
    val fontSizeLarge = 16.sp
    val fontSizeXLarge = 20.sp

    // Image size
    val imageSizeLarge = 96.dp

    // Spacing values
    val spacingXSmall = 4.dp
    val spacingSmall = 8.dp
    val spacingMedium = 16.dp
    val spacingLarge = 20.dp
    val spacingXLarge = 24.dp

    // Shadow values
    val shadowMedium = 8.dp
}

internal val LocalAppDimensions = staticCompositionLocalOf { AppDimensions }
