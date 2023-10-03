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

    fun observeExerciseCategory(id: Long): Flow<ExerciseCategory> {
        return exercisesRepository.observeExerciseCategory(id)
    }

    suspend fun saveExercise(
        name: String,
        exerciseCategoryId: Long,
        createdAt: Long,
        completedAt: Long,
        sets: Int? = null,
        reps: Int? = null,
        duration: Long? = null,
        score: Int
    ) {
        exercisesRepository.saveCompletedExercise(
            name,
            exerciseCategoryId,
            createdAt,
            completedAt,
            sets,
            reps,
            duration,
            score
        )
    }
}
