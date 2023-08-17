package com.example.fitness.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitness.feature.activity.navigation.navigateToActivity
import com.example.fitness.feature.exercisesettings.navigation.navigateToExerciseSettings
import com.example.fitness.feature.schedule.navigation.navigateToSchedule
import com.example.fitness.navigation.TopLevelDestination

@Composable
fun rememberFitnessAppState(
    navHostController: NavHostController = rememberNavController()
): FitnessAppState {
    return remember(navHostController) {
        FitnessAppState(navHostController)
    }
}

@Stable
class FitnessAppState(
    val navHostController: NavHostController
) {
    val topLevelDestination: List<TopLevelDestination> = TopLevelDestination.values().toList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        when (topLevelDestination) {
            TopLevelDestination.ACTIVITY -> navHostController.navigateToActivity()
            TopLevelDestination.SCHEDULE -> navHostController.navigateToSchedule()
            TopLevelDestination.EXERCISE_SETTINGS -> navHostController.navigateToExerciseSettings()
        }
    }
}