package com.example.fitness.core.model

data class ExerciseGroupItem(
    val id: Long,
    val name: String,
    val exerciseCategoryId: Long,
    val sets: Int?,
    val reps: Int?,
    val duration: Long?
)
