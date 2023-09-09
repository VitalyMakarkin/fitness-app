package com.example.fitness.feature.exercisecategories.model

import com.example.fitness.core.model.ExerciseCategory
import java.lang.StringBuilder

data class ExerciseCategoryUI(
    val id: Int,
    val name: String,
    val description: String?,
    val additionalInfo: String
)

internal fun ExerciseCategory.mapToExerciseCategoryUI() = with(this) {
    ExerciseCategoryUI(
        id,
        name,
        description,
        additionalInfo = formatExerciseCategoryAdditionalInfo(
            containsSets, containsReps, containsDuration
        )
    )
}

internal fun formatExerciseCategoryAdditionalInfo(
    containsSets: ExerciseCategory.RequiredState,
    containsReps: ExerciseCategory.RequiredState,
    containsDuration: ExerciseCategory.RequiredState
): String {
    val required = mutableListOf<String>()
    val optional = mutableListOf<String>()

    when (containsSets) {
        ExerciseCategory.RequiredState.REQUIRED -> required.add("sets")
        ExerciseCategory.RequiredState.OPTIONAL -> optional.add("sets")
        else -> {}
    }

    when (containsReps) {
        ExerciseCategory.RequiredState.REQUIRED -> required.add("reps")
        ExerciseCategory.RequiredState.OPTIONAL -> optional.add("reps")
        else -> {}
    }

    when (containsDuration) {
        ExerciseCategory.RequiredState.REQUIRED -> required.add("duration")
        ExerciseCategory.RequiredState.OPTIONAL -> optional.add("duration")
        else -> {}
    }

    return StringBuilder()
        .apply {
            if (required.isEmpty() && optional.isEmpty()) {
                append("without recommendations")
            } else {
                if (required.isNotEmpty()) {
                    append("required: ${required.joinToString(", ")}")
                    if (optional.isNotEmpty()) {
                        append(" ")
                    }
                }
                if (optional.isNotEmpty()) {
                    append("optional: ${optional.joinToString(", ")}")
                }
            }
        }
        .toString()
}
