package com.example.fitness.feature.exercisesettings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.exercisesettings.ExerciseSettingsRouter

const val exerciseSettingsRoute = "exercise_settings"

fun NavController.navigateToExerciseSettings() {
    this.navigate(exerciseSettingsRoute)
}

fun NavGraphBuilder.exerciseSettingsScreen() {
    composable(
        route = exerciseSettingsRoute
    ) {
        ExerciseSettingsRouter()
    }
}