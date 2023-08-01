package com.example.fitness.feature.exercisehistory

import com.example.fitness.core.model.Exercise

sealed interface ExerciseHistoryUiState {
    data class Success(val completedExercises: List<Exercise>) : ExerciseHistoryUiState
    object Loading : ExerciseHistoryUiState
}