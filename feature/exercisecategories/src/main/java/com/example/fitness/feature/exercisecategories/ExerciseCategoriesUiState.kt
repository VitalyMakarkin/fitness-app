package com.example.fitness.feature.exercisecategories

import com.example.fitness.feature.exercisecategories.model.ExerciseCategoryUI

sealed interface ExerciseCategoriesUiState {
    data class Success(val exerciseCategories: List<ExerciseCategoryUI>) : ExerciseCategoriesUiState
    object Loading : ExerciseCategoriesUiState
}
