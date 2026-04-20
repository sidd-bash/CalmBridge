package com.calmbridge.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.calmbridge.app.data.UserPreferencesManager
import com.calmbridge.app.theme.ThemeManager
import com.calmbridge.app.ui.HomeScreen
import com.calmbridge.app.ui.onboarding.OnboardingScreen

class MainActivity : ComponentActivity() {

    private lateinit var userPreferencesManager: UserPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPreferencesManager = UserPreferencesManager(this)

        setContent {
            var onboardingCompleted by remember {
                mutableStateOf(userPreferencesManager.isOnboardingCompleted())
            }
            var currentTheme by remember {
                mutableStateOf(userPreferencesManager.getSelectedTheme())
            }

            MaterialTheme(
                colorScheme = ThemeManager.getColorScheme(currentTheme)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (onboardingCompleted) {
                        HomeScreen(
                            onAddClick = { /* Handle Add */ },
                            onTopicClick = { /* Handle Topic Click */ }
                        )
                    } else {
                        OnboardingScreen(
                            onOnboardingComplete = { profile ->
                                userPreferencesManager.saveProfile(profile)
                                currentTheme = profile.selectedTheme
                                onboardingCompleted = true
                            }
                        )
                    }
                }
            }
        }
    }
}
