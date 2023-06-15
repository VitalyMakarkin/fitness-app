package com.example.fitness.domain.models

data class Exercise(
    val id: Int,
    val categoryId: Int?,
    val completedAt: Long
)