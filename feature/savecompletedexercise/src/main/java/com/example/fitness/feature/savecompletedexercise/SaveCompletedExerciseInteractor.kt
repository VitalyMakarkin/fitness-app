package com.example.fitness.feature.savecompletedexercise

import com.example.fitness.core.data.repository.ExercisesRepository
import com.example.fitness.core.model.ExerciseCategory
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SaveCompletedExerciseInteractor @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) {

    fun observeExerciseCategories(): Flow<List<ExerciseCategory>> {
        return exercisesRepository.observeExerciseCategories()
    }

    suspend fun saveExercise() {
        exercisesRepository.saveCompletedExercise()
    }
}