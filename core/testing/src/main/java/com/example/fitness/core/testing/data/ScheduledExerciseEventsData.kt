package com.example.fitness.core.testing.data

import com.example.fitness.core.model.ExerciseGroup
import com.example.fitness.core.model.ScheduledExerciseEvent

val ScheduledExerciseEventsData = listOf(
    ScheduledExerciseEvent(
        id = 1,
        scheduledAt = 1000L,
        exerciseGroup = ExerciseGroup(
            id = 1,
            name = "Exercise Group 1",
            exercises = emptyList()
        )
    )
)