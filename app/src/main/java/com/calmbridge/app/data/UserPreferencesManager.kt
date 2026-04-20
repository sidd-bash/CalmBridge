package com.calmbridge.app.data

import android.content.Context
import android.content.SharedPreferences
import com.calmbridge.app.model.UserProfile

class UserPreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("calmbridge_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_DOMAIN = "domain"
        private const val KEY_INTENT = "intent"
        private const val KEY_STYLE = "style"
        private const val KEY_PAIN_POINT = "pain_point"
        private const val KEY_SELECTED_THEME = "selected_theme"
        private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
    }

    fun saveProfile(profile: UserProfile) {
        prefs.edit().apply {
            putString(KEY_DOMAIN, profile.domain)
            putString(KEY_INTENT, profile.intent)
            putString(KEY_STYLE, profile.style)
            putString(KEY_PAIN_POINT, profile.painPoint)
            putString(KEY_SELECTED_THEME, profile.selectedTheme)
            putBoolean(KEY_ONBOARDING_COMPLETED, true)
            apply()
        }
    }

    fun loadProfile(): UserProfile? {
        val domain = prefs.getString(KEY_DOMAIN, null) ?: return null
        return UserProfile(
            domain = domain,
            intent = prefs.getString(KEY_INTENT, "") ?: "",
            style = prefs.getString(KEY_STYLE, "") ?: "",
            painPoint = prefs.getString(KEY_PAIN_POINT, "") ?: "",
            selectedTheme = prefs.getString(KEY_SELECTED_THEME, "ocean_clarity") ?: "ocean_clarity"
        )
    }

    fun isOnboardingCompleted(): Boolean {
        return prefs.getBoolean(KEY_ONBOARDING_COMPLETED, false)
    }

    fun getSelectedTheme(): String {
        return prefs.getString(KEY_SELECTED_THEME, "ocean_clarity") ?: "ocean_clarity"
    }
}
