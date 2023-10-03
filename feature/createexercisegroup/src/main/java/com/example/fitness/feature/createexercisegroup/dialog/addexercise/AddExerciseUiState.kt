package com.example.fitness.feature.createexercisegroup.dialog.addexercise

import com.example.fitness.core.model.ExerciseCategory

sealed interface AddExerciseUiState {

    data class Success(
        val selectedExerciseCategory: ExerciseCategory?
    ) : AddExerciseUiState

    object Loading : AddExerciseUiState
}
