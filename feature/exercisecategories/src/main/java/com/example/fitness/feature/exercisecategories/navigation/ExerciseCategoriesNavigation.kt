package com.example.fitness.feature.exercisecategories.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitness.feature.exercisecategories.ExerciseCategoriesRouter

const val exerciseCategoriesRoute = "exercise_categories"

fun NavController.navigateToExerciseCategories() {
    this.navigate(exerciseCategoriesRoute)
}

fun NavGraphBuilder.exerciseCategoriesScreen(
    onBackClick: () -> Unit,
    onNewExerciseCategoryCreateClick: () -> Unit,
) {
    composable(
        route = exerciseCategoriesRoute
    ) {
        ExerciseCategoriesRouter(
            onBackClick = onBackClick,
            onNewExerciseCategoryCreateClick = onNewExerciseCategoryCreateClick
        )
    }
}
