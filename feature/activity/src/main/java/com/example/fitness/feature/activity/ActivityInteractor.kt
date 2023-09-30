package com.example.fitness.feature.activity

import com.example.fitness.core.data.repository.ExercisesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ActivityInteractor @Inject constructor(
    private val exerciseRepository: ExercisesRepository
) {

    fun observeExercisesCount(): Flow<Int> {
        return exerciseRepository.observeExercisesCount()
    }

    fun observeExercisesAverageScore(lastExerciseCount: Int): Flow<Double> {
        return exerciseRepository.observeExercisesAverageScore(lastExerciseCount)
    }
}