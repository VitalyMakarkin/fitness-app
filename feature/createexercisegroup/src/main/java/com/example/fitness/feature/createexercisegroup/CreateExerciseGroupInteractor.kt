package com.example.fitness.feature.createexercisegroup

import com.example.fitness.core.data.repository.ExercisesRepository
import javax.inject.Inject

class CreateExerciseGroupInteractor @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) {

    suspend fun createExerciseGroup(name: String, ) {
        exercisesRepository.createExerciseGroup(name)
    }
}