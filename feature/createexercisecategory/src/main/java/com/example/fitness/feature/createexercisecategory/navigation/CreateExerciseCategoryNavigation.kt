package com.example.fitness.feature.createexercisecategory.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.createexercisecategory.CreateExerciseCategoryRouter

const val createExerciseCategoryRoute = "create_exercise_category"

fun NavController.navigateToCreateExerciseCategory() {
    this.navigate(createExerciseCategoryRoute)
}

fun NavGraphBuilder.createExerciseCategoryScreen() {
    composable(
        route = createExerciseCategoryRoute
    ) {
        CreateExerciseCategoryRouter()
    }
}