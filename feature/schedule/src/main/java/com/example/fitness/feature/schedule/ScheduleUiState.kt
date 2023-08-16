package com.example.fitness.feature.schedule

import com.example.fitness.core.model.ScheduledExerciseEvent

sealed interface ScheduleUiState {
    data class Success(val events: List<ScheduledExerciseEvent>) : ScheduleUiState
    object Loading : ScheduleUiState
}