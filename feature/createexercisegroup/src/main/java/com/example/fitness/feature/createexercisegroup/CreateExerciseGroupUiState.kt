package com.example.fitness.feature.createexercisegroup

import com.example.fitness.core.model.ExerciseGroupItem

sealed interface CreateExerciseGroupUiState {

    data class Success(
        val exercises: List<ExerciseGroupItem>
    ): CreateExerciseGroupUiState

    object Loading: CreateExerciseGroupUiState
}