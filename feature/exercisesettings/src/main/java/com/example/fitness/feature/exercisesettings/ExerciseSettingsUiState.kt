package com.example.fitness.feature.exercisesettings

sealed interface ExerciseSettingsUiState {
    data class Success(val activitiesCount: Int) : ExerciseSettingsUiState
    object Loading : ExerciseSettingsUiState
}