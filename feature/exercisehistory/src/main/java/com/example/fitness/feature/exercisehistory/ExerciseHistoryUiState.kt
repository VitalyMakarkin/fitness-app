package com.example.fitness.feature.exercisehistory

import com.example.fitness.feature.exercisehistory.model.ExerciseUI

sealed interface ExerciseHistoryUiState {
    data class Success(val completedExercises: List<ExerciseUI>) : ExerciseHistoryUiState
    object Loading : ExerciseHistoryUiState
}
