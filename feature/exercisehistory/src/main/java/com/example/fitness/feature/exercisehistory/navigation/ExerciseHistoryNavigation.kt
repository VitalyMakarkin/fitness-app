package com.example.fitness.feature.exercisehistory.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.exercisehistory.ExercisesHistoryRoute

const val exerciseHistoryRoute = "exercise_history"

fun NavGraphBuilder.exerciseHistoryScreen() {
    composable(
        route = exerciseHistoryRoute
    ) {
        ExercisesHistoryRoute()
    }
}