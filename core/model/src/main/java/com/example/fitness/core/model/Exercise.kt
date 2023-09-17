package com.example.fitness.core.model

data class Exercise(
    val id: Long,
    val name: String,
    val exerciseCategoryId: Long,
    val createdAt: Long,
    val completedAt: Long,
    val sets: Int?,
    val reps: Int?,
    val duration: Long?,
    val score: Int
)