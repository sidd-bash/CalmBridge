package com.calmbridge.app.theme

import androidx.compose.ui.graphics.Color

data class AppThemeColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val background: Color,
    val onPrimary: Color = Color.White,
    val onSecondary: Color = Color.Black,
    val onTertiary: Color = Color.White,
    val onBackground: Color = Color.Black,
    val surface: Color = background,
    val onSurface: Color = onBackground
)

object CalmBridgeThemes {
    val sageCalm = AppThemeColors(
        primary = Color(0xFF7FBF9F),
        secondary = Color(0xFFD6EDE3),
        tertiary = Color(0xFF4A7C6F),
        background = Color(0xFFF7FAF9)
    )

    val focusedEnergy = AppThemeColors(
        primary = Color(0xFFFFB703),
        secondary = Color(0xFFFFE8A3),
        tertiary = Color(0xFFFB8500),
        background = Color(0xFFFFF8E6)
    )

    val deepFocus = AppThemeColors(
        primary = Color(0xFF1E293B),
        secondary = Color(0xFF334155),
        tertiary = Color(0xFF94A3B8),
        background = Color(0xFF0F172A),
        onPrimary = Color.White,
        onSecondary = Color.White,
        onTertiary = Color.White,
        onBackground = Color.White,
        surface = Color(0xFF0F172A),
        onSurface = Color.White
    )

    val creativeFlow = AppThemeColors(
        primary = Color(0xFFE5989B),
        secondary = Color(0xFFFFD6D6),
        tertiary = Color(0xFFB5838D),
        background = Color(0xFFFFF5F5)
    )

    val oceanClarity = AppThemeColors(
        primary = Color(0xFF5DADE2),
        secondary = Color(0xFFD6EAF8),
        tertiary = Color(0xFF2E86C1),
        background = Color(0xFFF4F8FB)
    )

    fun getThemeByName(name: String): AppThemeColors {
        return when (name) {
            "sage_calm" -> sageCalm
            "focused_energy" -> focusedEnergy
            "deep_focus" -> deepFocus
            "creative_flow" -> creativeFlow
            "ocean_clarity" -> oceanClarity
            else -> oceanClarity
        }
    }
}
