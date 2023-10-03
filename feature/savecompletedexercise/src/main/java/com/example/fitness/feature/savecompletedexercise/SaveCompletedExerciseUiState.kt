package com.example.fitness.feature.savecompletedexercise

import com.example.fitness.core.model.ExerciseCategory

sealed interface SaveCompletedExerciseUiState {

    data class Success(
        val selectedExerciseCategory: ExerciseCategory?,
        val selectedCompletedAt: Long,
    ) : SaveCompletedExerciseUiState

    object Loading : SaveCompletedExerciseUiState
}
