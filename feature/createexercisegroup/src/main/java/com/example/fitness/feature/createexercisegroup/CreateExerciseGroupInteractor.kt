package com.example.fitness.feature.createexercisegroup

import com.example.fitness.core.data.repository.ExercisesRepository
import javax.inject.Inject

class CreateExerciseGroupInteractor @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) {

    suspend fun createExerciseGroup(
        name: String,
        description: String?,
        containsSets: Boolean,
        containsReps: Boolean,
        containsDuration: Boolean
    ) {
        exercisesRepository.createExerciseCategory(
            name,
            description,
            containsSets,
            containsReps,
            containsDuration
        )
    }
}