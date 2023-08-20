package com.example.fitness.feature.createexercisecategory

sealed interface CreateExerciseCategoryUiState {
    object Success : CreateExerciseCategoryUiState
    object Loading : CreateExerciseCategoryUiState
}