package com.example.fitness.feature.exercisesettings

sealed interface ExerciseSettingsUiState {

    data class Success(
        val activitiesCount: Int,
        val exerciseCategoriesCount: Int
    ) : ExerciseSettingsUiState

    object Loading : ExerciseSettingsUiState
}