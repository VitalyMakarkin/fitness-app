package com.example.fitness.core.data.mapper

import com.example.fitness.core.database.models.ExerciseEntity
import com.example.fitness.core.model.Exercise

fun Exercise.mapToExerciseEntity() = with(this) {
    ExerciseEntity(
        id,
        exerciseCategoryId,
        createdAt,
        completedAt,
        sets,
        reps,
        duration,
        score
    )
}

fun ExerciseEntity.mapToExercise() = with(this) {
    Exercise(
        id,
        exerciseCategoryId,
        createdAt,
        completedAt,
        sets,
        reps,
        duration,
        score
    )
}