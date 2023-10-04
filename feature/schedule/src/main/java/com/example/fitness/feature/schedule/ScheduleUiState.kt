package com.example.fitness.feature.schedule

import com.example.fitness.feature.schedule.model.ScheduledEventUI

sealed interface ScheduleUiState {
    data class Success(val events: List<ScheduledEventUI>) : ScheduleUiState
    object Loading : ScheduleUiState
}
