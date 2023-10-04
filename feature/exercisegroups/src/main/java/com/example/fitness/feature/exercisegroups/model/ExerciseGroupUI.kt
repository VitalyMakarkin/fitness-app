package com.example.fitness.feature.exercisegroups.model

import com.example.fitness.core.model.ExerciseGroup

data class ExerciseGroupUI(
    val id: Long,
    val name: String,
    val exerciseCount: Int
)

internal fun ExerciseGroup.mapToExerciseGroupUI() = with(this) {
    ExerciseGroupUI(
        id,
        name,
        exerciseCount = exercises.size
    )
}
