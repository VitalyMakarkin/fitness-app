package com.example.fitness.feature.savecompletedexercise.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.savecompletedexercise.SaveCompletedExerciseRouter

const val saveCompletedExerciseRoute = "save_completed_exercise"

fun NavController.navigateToSaveCompletedExercise() {
    this.navigate(saveCompletedExerciseRoute)
}

fun NavGraphBuilder.saveCompletedExerciseScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = saveCompletedExerciseRoute
    ) {
        SaveCompletedExerciseRouter(
            onBackClick = onBackClick
        )
    }
}