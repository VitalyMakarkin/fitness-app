package com.example.fitness.feature.schedule.model

import com.example.fitness.core.model.ScheduledExerciseEvent

data class ScheduledEventUI(
    val id: Int,
    val scheduledAt: Long,
    val exerciseGroupName: String
)

fun ScheduledExerciseEvent.mapToScheduledEventUI() = with(this) {
    ScheduledEventUI(
        id = id,
        scheduledAt = scheduledAt,
        exerciseGroupName = exerciseGroup.name
    )
}