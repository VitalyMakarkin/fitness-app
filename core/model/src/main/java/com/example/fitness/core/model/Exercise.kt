package com.example.fitness.core.model

data class Exercise(
    val id: Int,
    val categoryId: Int?,
    val completedAt: Long
)