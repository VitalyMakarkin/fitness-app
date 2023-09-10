package com.example.fitness.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fitness.R

enum class TopLevelDestination(
    val imageVector: ImageVector,
    val labelResId: Int
) {
    ACTIVITY(
        imageVector = Icons.Default.Star,
        labelResId = R.string.top_level_destination_activity
    ),

    SCHEDULE(
        imageVector = Icons.Default.List,
        labelResId = R.string.top_level_destination_schedule
    ),

    EXERCISE_SETTINGS(
        imageVector = Icons.Default.Settings,
        labelResId = R.string.top_level_destination_settings
    )
}