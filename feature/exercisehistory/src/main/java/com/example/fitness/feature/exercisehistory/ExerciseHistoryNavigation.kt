package com.example.fitness.feature.exercisehistory

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val exerciseHistoryRoute = "exercise_history"

fun NavGraphBuilder.exerciseHistoryScreen() {
    composable(
        route = exerciseHistoryRoute,
    ) {
        ExercisesHistoryRoute()
    }
}