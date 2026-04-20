package com.calmbridge.app.ui.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.calmbridge.app.model.UserProfile
import com.calmbridge.app.theme.ThemeManager

@Composable
fun OnboardingScreen(
    onOnboardingComplete: (UserProfile) -> Unit
) {
    var currentStep by remember { mutableIntStateOf(0) }
    
    var domain by remember { mutableStateOf("") }
    var intent by remember { mutableStateOf("") }
    var style by remember { mutableStateOf("") }
    var painPoint by remember { mutableStateOf("") }

    val steps = listOf(
        Question("Domain", listOf("Fitness", "Productivity", "Finance", "Cooking", "Creative", "Mixed"), domain) { domain = it },
        Question("Intent", listOf("Learn", "Use later", "Inspiration", "Just browsing"), intent) { intent = it },
        Question("Style", listOf("Quick summaries", "Step-by-step", "Deep explanations", "Visual"), style) { style = it },
        Question("Pain Point", listOf("Too much info", "Forgetting", "No structure", "Don't know what matters"), painPoint) { painPoint = it }
    )

    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    if (currentStep < steps.size - 1) {
                        currentStep++
                    } else {
                        val profile = UserProfile(domain, intent, style, painPoint, "")
                        val theme = ThemeManager.mapProfileToTheme(profile)
                        onOnboardingComplete(profile.copy(selectedTheme = theme))
                    }
                },
                enabled = steps[currentStep].selectedOption.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(if (currentStep == steps.size - 1) "Finish" else "Next")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Step ${currentStep + 1} of ${steps.size}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            AnimatedContent(targetState = currentStep, label = "QuestionAnimation") { step ->
                QuestionCard(steps[step])
            }
        }
    }
}

@Composable
fun QuestionCard(question: Question) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "What's your ${question.title.lowercase()}?",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Column(Modifier.selectableGroup()) {
            question.options.forEach { option ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (option == question.selectedOption),
                            onClick = { question.onOptionSelected(option) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (option == question.selectedOption),
                        onClick = null // null recommended for accessibility with screenreaders
                    )
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

data class Question(
    val title: String,
    val options: List<String>,
    val selectedOption: String,
    val onOptionSelected: (String) -> Unit
)
