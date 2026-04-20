package com.calmbridge.app.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.calmbridge.app.model.UserProfile

object ThemeManager {

    fun mapProfileToTheme(profile: UserProfile): String {
        return when (profile.domain) {
            "Fitness" -> "focused_energy"
            "Productivity" -> "sage_calm"
            "Finance" -> "deep_focus"
            "Creative" -> "creative_flow"
            "Mixed" -> "ocean_clarity"
            else -> "ocean_clarity"
        }
    }

    fun getColorScheme(themeName: String): ColorScheme {
        val colors = CalmBridgeThemes.getThemeByName(themeName)
        
        // Use darkColorScheme for deep_focus, lightColorScheme for others as a simplification
        return if (themeName == "deep_focus") {
            darkColorScheme(
                primary = colors.primary,
                secondary = colors.secondary,
                tertiary = colors.tertiary,
                background = colors.background,
                onPrimary = colors.onPrimary,
                onSecondary = colors.onSecondary,
                onTertiary = colors.onTertiary,
                onBackground = colors.onBackground,
                surface = colors.surface,
                onSurface = colors.onSurface
            )
        } else {
            lightColorScheme(
                primary = colors.primary,
                secondary = colors.secondary,
                tertiary = colors.tertiary,
                background = colors.background,
                onPrimary = colors.onPrimary,
                onSecondary = colors.onSecondary,
                onTertiary = colors.onTertiary,
                onBackground = colors.onBackground,
                surface = colors.surface,
                onSurface = colors.onSurface
            )
        }
    }
}
