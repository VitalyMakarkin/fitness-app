package com.example.fitness.core.model

data class ExerciseSchedule(
    val id: Int,
    val scheduledAt: Long,
    val exerciseGroup: ExerciseGroup
)
