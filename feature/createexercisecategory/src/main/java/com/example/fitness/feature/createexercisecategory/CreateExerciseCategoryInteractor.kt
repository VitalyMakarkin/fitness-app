package com.example.fitness.feature.createexercisecategory

import com.example.fitness.core.data.repository.ExercisesRepository
import javax.inject.Inject

class CreateExerciseCategoryInteractor @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) {

    suspend fun createExerciseCategory(name: String, description: String?) {
        exercisesRepository.createExerciseCategory(name, description)
    }
}