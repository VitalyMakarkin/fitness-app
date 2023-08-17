package com.example.fitness.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val imageVector: ImageVector,
    val label: String
) {
    ACTIVITY(
        imageVector = Icons.Default.Star,
        label = "Activity"
    ),

    SCHEDULE(
        imageVector = Icons.Default.List,
        label = "Schedule"
    ),

    EXERCISE_SETTINGS(
        imageVector = Icons.Default.Settings,
        label = "Settings"
    )
}