package com.example.fitness.feature.createexercisegroup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.createexercisegroup.CreateExerciseGroupRouter

const val createExerciseGroupRoute = "create_exercise_group"

fun NavController.navigateToCreateExerciseGroup() {
    this.navigate(createExerciseGroupRoute)
}

fun NavGraphBuilder.createExerciseGroupScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = createExerciseGroupRoute
    ) {
        CreateExerciseGroupRouter(
            onBackClick = onBackClick
        )
    }
}