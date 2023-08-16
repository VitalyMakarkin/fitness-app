package com.example.fitness.feature.activity

import com.example.fitness.core.data.repository.ExercisesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ActivityInteractor @Inject constructor(
    private val exerciseHistoryRepository: ExercisesRepository
) {

    fun getExercisesCount(): Flow<Int> {
        return exerciseHistoryRepository.getExercisesCount()
    }
}