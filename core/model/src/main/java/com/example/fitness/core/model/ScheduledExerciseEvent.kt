package com.example.fitness.core.model

data class ScheduledExerciseEvent(
    val id: Long,
    val scheduledAt: Long,
    val exerciseGroup: ExerciseGroup
)
