package com.example.fitness.feature.exercisegroups.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.exercisegroups.ExerciseGroupsRouter

const val exerciseGroupsRoute = "exercise_groups"

fun NavController.navigateToExerciseGroups() {
    this.navigate(exerciseGroupsRoute)
}

fun NavGraphBuilder.exerciseGroupsScreen() {
    composable(
        route = exerciseGroupsRoute
    ) {
        ExerciseGroupsRouter()
    }
}