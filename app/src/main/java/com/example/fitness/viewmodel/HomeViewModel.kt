package com.example.fitness.viewmodel

import com.example.fitness.core.data.repository.ExerciseHistoryRepository
import javax.inject.Inject

/**
 * Concept
 */
class HomeViewModel @Inject constructor(
    private val repository: ExerciseHistoryRepository
) {

    // Show avatar and name
    fun getProfile() {
        TODO("Not implemented")
    }

    // For show analytics in chart (average score on day/week/month/year/all)
    fun getAllExercises() {
        TODO("Not implemented")
    }

    // From add button
    fun addExercise() {
        TODO("Not implemented")
    }
}
