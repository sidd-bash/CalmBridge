package com.calmbridge.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.calmbridge.app.theme.ThemeManager
import androidx.compose.foundation.lazy.items
import com.calmbridge.app.R
import com.calmbridge.app.data.TopicRepository
import com.calmbridge.app.model.Topic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onAddClick: () -> Unit = {},
    onTopicClick: (Topic) -> Unit = {}
) {
    val context = LocalContext.current
    val topics = remember { TopicRepository.loadTopics(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(stringResource(R.string.app_name))
                        Text(
                            text = "Your clarity space",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            )
        },

        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Text("🏠") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Text("🔍") },
                    label = { Text("Explore") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Text("👤") },
                    label = { Text("Profile") }
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Text("+")
            }
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(topics) { topic ->
                TopicCard(
                    topic = topic,
                    onClick = { onTopicClick(topic) }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Sage Calm")
@Composable
fun SageCalmPreview() {
    MaterialTheme(colorScheme = ThemeManager.getColorScheme("sage_calm")) {
        HomeScreen()
    }
}

@Preview(showBackground = true, name = "Deep Focus")
@Composable
fun DeepFocusPreview() {
    MaterialTheme(colorScheme = ThemeManager.getColorScheme("deep_focus")) {
        HomeScreen()
    }
}

@Preview(showBackground = true, name = "Focused Energy")
@Composable
fun FocusedEnergyPreview() {
    MaterialTheme(colorScheme = ThemeManager.getColorScheme("focused_energy")) {
        HomeScreen()
    }
}

@Preview(showBackground = true, name = "Creative Flow")
@Composable
fun CreativeFlowPreview() {
    MaterialTheme(colorScheme = ThemeManager.getColorScheme("creative_flow")) {
        HomeScreen()
    }
}

@Preview(showBackground = true, name = "Ocean Clarity")
@Composable
fun OceanClarityPreview() {
    MaterialTheme(colorScheme = ThemeManager.getColorScheme("ocean_clarity")) {
        HomeScreen()
    }
}

@Composable
fun TopicCard(
    topic: Topic,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            // Title
            Text(
                text = topic.title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Summary
            Text(
                text = topic.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Footer Row
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${topic.count} reels",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }
        }
    }
}
