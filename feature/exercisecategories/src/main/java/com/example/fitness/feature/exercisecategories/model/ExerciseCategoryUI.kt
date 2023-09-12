package com.example.fitness.feature.exercisecategories.model

import com.example.fitness.core.model.ExerciseCategory

data class ExerciseCategoryUI(
    val id: Int,
    val name: String,
    val description: String?
)

internal fun ExerciseCategory.mapToExerciseCategoryUI() = with(this) {
    ExerciseCategoryUI(id, name, description)
}
