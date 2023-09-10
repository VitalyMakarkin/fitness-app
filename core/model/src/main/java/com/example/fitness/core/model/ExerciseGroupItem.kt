package com.example.fitness.core.model

data class ExerciseGroupItem(
    val id: Int,
    val name: String,
    val exerciseCategoryId: Int,
    val sets: Int?,
    val reps: Int?,
    val duration: Long?
)