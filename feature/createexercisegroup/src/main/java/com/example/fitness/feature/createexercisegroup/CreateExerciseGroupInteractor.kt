package com.example.fitness.feature.createexercisegroup

import com.example.fitness.core.data.repository.ExercisesRepository
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.core.model.ExerciseGroupItem
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class CreateExerciseGroupInteractor @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) {

    suspend fun createExerciseGroup(name: String, exercises: List<ExerciseGroupItem>) {
        exercisesRepository.createExerciseGroup(name, exercises)
    }

    fun observeExerciseCategories(): Flow<List<ExerciseCategory>> {
        return exercisesRepository.observeExerciseCategories()
    }

    fun observeExerciseCategory(id: Long): Flow<ExerciseCategory> {
        return exercisesRepository.observeExerciseCategory(id)
    }
}
