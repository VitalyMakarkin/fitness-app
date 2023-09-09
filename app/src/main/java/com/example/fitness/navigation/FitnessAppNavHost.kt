package com.example.fitness.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.fitness.feature.activity.navigation.activityRoute
import com.example.fitness.feature.activity.navigation.activityScreen
import com.example.fitness.feature.createexercisecategory.navigation.createExerciseCategoryScreen
import com.example.fitness.feature.createexercisecategory.navigation.navigateToCreateExerciseCategory
import com.example.fitness.feature.createexercisegroup.navigation.createExerciseGroupScreen
import com.example.fitness.feature.createexercisegroup.navigation.navigateToCreateExerciseGroup
import com.example.fitness.feature.exercisecategories.navigation.exerciseCategoriesScreen
import com.example.fitness.feature.exercisecategories.navigation.navigateToExerciseCategories
import com.example.fitness.feature.exercisegroups.navigation.exerciseGroupsScreen
import com.example.fitness.feature.exercisegroups.navigation.navigateToExerciseGroups
import com.example.fitness.feature.exercisehistory.navigation.exerciseHistoryScreen
import com.example.fitness.feature.exercisehistory.navigation.navigateToExerciseHistory
import com.example.fitness.ui.FitnessAppState
import com.example.fitness.feature.exercisesettings.navigation.exerciseSettingsScreen
import com.example.fitness.feature.savecompletedexercise.navigation.navigateToSaveCompletedExercise
import com.example.fitness.feature.savecompletedexercise.navigation.saveCompletedExerciseScreen
import com.example.fitness.feature.schedule.navigation.scheduleScreen

@Composable
fun FitnessAppNavHost(
    appState: FitnessAppState,
    modifier: Modifier = Modifier,
    startDestination: String = activityRoute
) {
    val navController = appState.navHostController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        activityScreen(
            onSaveCompletedExerciseClick = { navController.navigateToSaveCompletedExercise() }
        )
        scheduleScreen()
        exerciseSettingsScreen(
            onExerciseHistoryClick = { navController.navigateToExerciseHistory() },
            onExerciseCategoriesClick = { navController.navigateToExerciseCategories() },
            onExerciseGroupsClick = { navController.navigateToExerciseGroups() },
            nestedScreens = {
                exerciseHistoryScreen(
                    onBackClick = { navController.popBackStack() },
                    onSaveCompletedExerciseClick = { navController.navigateToSaveCompletedExercise() }
                )
                exerciseCategoriesScreen(
                    onBackClick = { navController.popBackStack() },
                    onNewExerciseCategoryCreateClick = { navController.navigateToCreateExerciseCategory() }
                )
                exerciseGroupsScreen(
                    onBackClick = { navController.popBackStack() },
                    onNewExerciseGroupCreateClick = { navController.navigateToCreateExerciseGroup() }
                )
                createExerciseCategoryScreen(
                    onBackClick = { navController.popBackStack() },
                )
                createExerciseGroupScreen(
                    onBackClick = { navController.popBackStack() }
                )
                saveCompletedExerciseScreen(
                    onBackClick = { navController.popBackStack() },
                )
            }
        )
    }
}