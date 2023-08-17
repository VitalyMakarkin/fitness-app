package com.example.fitness.feature.activity.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.activity.ActivityRoute

const val activityRoute = "activity"

fun NavController.navigateToActivity() {
    this.navigate(activityRoute)
}

fun NavGraphBuilder.activityScreen() {
    composable(
        route = activityRoute
    ) {
        ActivityRoute()
    }
}