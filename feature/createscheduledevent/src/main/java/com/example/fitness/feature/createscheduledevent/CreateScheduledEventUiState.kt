package com.example.fitness.feature.createscheduledevent

import com.example.fitness.core.model.ExerciseGroup

sealed interface CreateScheduledEventUiState {

    data class Success(
        val selectedScheduledAt: Long,
        val selectedExerciseGroup: ExerciseGroup?,
    ) : CreateScheduledEventUiState

    object Loading : CreateScheduledEventUiState
}
