package com.example.fitness.core.model

data class ExerciseGroup(
    val id: Int,
    val name: String,
    val exercises: List<Exercise>
)
