package com.example.fitness.feature.exercisehistory.model

import com.example.fitness.core.model.Exercise

data class ExerciseUI(
    val id: Long,
    val name: String,
    val completedAt: Long
)

internal fun Exercise.mapToExerciseUI() = with(this) {
    ExerciseUI(id, name, completedAt)
}