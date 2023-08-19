package com.example.fitness.feature.exercisehistory

import com.example.fitness.core.data.repository.ExercisesRepository
import com.example.fitness.core.model.Exercise
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ExerciseHistoryInteractor @Inject constructor(
    private val exerciseRepository: ExercisesRepository
) {

    fun observeExercises(): Flow<List<Exercise>> {
        return exerciseRepository.observeExercises()
    }
}