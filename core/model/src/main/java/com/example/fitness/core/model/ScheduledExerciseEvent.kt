package com.example.fitness.core.model

data class ScheduledExerciseEvent(
    val id: Int,
    val scheduledAt: Long,
    val exerciseGroup: ExerciseGroup
)
