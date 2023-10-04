package com.example.fitness.feature.createscheduledevent.dialog.exercisegroupselection

import com.example.fitness.core.model.ExerciseGroup

sealed interface ExerciseGroupSelectionUiState {

    data class Success(
        val groups: List<ExerciseGroup>
    ) : ExerciseGroupSelectionUiState

    object Loading : ExerciseGroupSelectionUiState
}
