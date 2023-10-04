package com.example.fitness.feature.exercisehistory.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.exercisehistory.ExercisesHistoryRoute

const val exerciseHistoryRoute = "exercise_history"

fun NavController.navigateToExerciseHistory() {
    this.navigate(exerciseHistoryRoute)
}

fun NavGraphBuilder.exerciseHistoryScreen(
    onBackClick: () -> Unit,
    onSaveCompletedExerciseClick: () -> Unit
) {
    composable(
        route = exerciseHistoryRoute
    ) {
        ExercisesHistoryRoute(
            onBackClick = onBackClick,
            onSaveCompletedExerciseClick = onSaveCompletedExerciseClick
        )
    }
}
