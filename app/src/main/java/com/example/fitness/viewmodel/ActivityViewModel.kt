package com.example.fitness.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fitness.core.data.repository.ExercisesRepository
import javax.inject.Inject

/**
 * Concept
 */
class ActivityViewModel @Inject constructor(
    private val repository: ExercisesRepository
) : ViewModel() {

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
