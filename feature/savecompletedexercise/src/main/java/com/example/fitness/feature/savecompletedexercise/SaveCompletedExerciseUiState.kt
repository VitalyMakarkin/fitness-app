package com.example.fitness.feature.savecompletedexercise

import com.example.fitness.core.model.ExerciseCategory

sealed interface SaveCompletedExerciseUiState {

    data class Success(
        val exerciseCategories: List<ExerciseCategory>
    ) : SaveCompletedExerciseUiState

    object Loading : SaveCompletedExerciseUiState
}