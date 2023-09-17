package com.example.fitness.core.model

data class ExerciseGroup(
    val id: Long,
    val name: String,
    val exercises: List<ExerciseGroupItem>
)
