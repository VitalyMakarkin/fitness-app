package com.example.fitness.feature.exercisegroups

import com.example.fitness.feature.exercisegroups.model.ExerciseGroupUI

sealed interface ExerciseGroupsUiState {
    data class Success(val exerciseGroups: List<ExerciseGroupUI>) : ExerciseGroupsUiState
    object Loading : ExerciseGroupsUiState
}