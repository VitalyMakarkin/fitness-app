package com.example.fitness.feature.exercisecategories

import com.example.fitness.core.data.repository.ExercisesRepository
import com.example.fitness.core.model.ExerciseCategory
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ExerciseCategoriesInteractor @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) {

    fun observeExerciseCategories(): Flow<List<ExerciseCategory>> {
        return exercisesRepository.observeExerciseCategories()
    }

    suspend fun updateRemoteExerciseCategories() {
        exercisesRepository.updateAllCategories()
    }

    suspend fun deleteExerciseCategory(id: Long) {
        exercisesRepository.deleteExerciseCategory(id)
    }
}
