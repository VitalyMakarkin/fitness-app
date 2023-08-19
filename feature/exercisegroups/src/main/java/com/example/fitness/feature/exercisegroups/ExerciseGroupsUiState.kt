package com.example.fitness.feature.exercisegroups

import com.example.fitness.core.model.ExerciseGroup

sealed interface ExerciseGroupsUiState {
    data class Success(val exerciseGroups: List<ExerciseGroup>) : ExerciseGroupsUiState
    object Loading : ExerciseGroupsUiState
}