package com.example.newsapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Platinum,
    onPrimary = NavyDark,
    primaryContainer = BlueGreyDark,
    onPrimaryContainer = Platinum,
    secondary = Silver,
    onSecondary = NavyDark,
    background = NavyDark,
    surface = BlueGreyDark,
    onBackground = Platinum,
    onSurface = Platinum,
    surfaceVariant = NavyDark,
    onSurfaceVariant = Platinum
)

private val LightColorScheme = lightColorScheme(
    primary = DeepNavy,
    onPrimary = Color.White,
    primaryContainer = Platinum,
    onPrimaryContainer = DeepNavy,
    secondary = MutedBlue,
    onSecondary = Color.White,
    background = CreamBackground,
    surface = Color.White,
    onBackground = DarkText,
    onSurface = DarkText,
    surfaceVariant = Color(0xFFF1F3F5),
    onSurfaceVariant = DeepNavy
)

@Composable
fun NewsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
