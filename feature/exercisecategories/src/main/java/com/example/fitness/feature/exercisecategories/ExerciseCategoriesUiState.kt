package com.example.fitness.feature.exercisecategories

import com.example.fitness.core.model.ExerciseCategory

sealed interface ExerciseCategoriesUiState {
    data class Success(val exerciseCategories: List<ExerciseCategory>) : ExerciseCategoriesUiState
    object Loading : ExerciseCategoriesUiState
}