package com.example.fitness.feature.exercisesettings

sealed interface ExerciseSettingsUiState {

    data class Success(
        val activitiesCount: Int,
        val exerciseCategoriesCount: Int,
        val exerciseGroupsCount: Int
    ) : ExerciseSettingsUiState

    object Loading : ExerciseSettingsUiState
}
