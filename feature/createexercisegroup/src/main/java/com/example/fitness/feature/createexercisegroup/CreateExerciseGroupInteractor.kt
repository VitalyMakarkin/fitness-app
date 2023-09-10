package com.example.fitness.feature.createexercisegroup

import com.example.fitness.core.data.repository.ExercisesRepository
import com.example.fitness.core.model.ExerciseCategory
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class CreateExerciseGroupInteractor @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) {

    suspend fun createExerciseGroup(name: String, ) {
        exercisesRepository.createExerciseGroup(name)
    }

    fun observeExerciseCategories(): Flow<List<ExerciseCategory>> {
        return exercisesRepository.observeExerciseCategories()
    }

    fun observeExerciseCategory(id: Int): Flow<ExerciseCategory> {
        return exercisesRepository.observeExerciseCategory(id)
    }
}