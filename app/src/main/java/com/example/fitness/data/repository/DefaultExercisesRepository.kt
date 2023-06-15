package com.example.fitness.data.repository

import com.example.fitness.core.database.dao.ExerciseCategoryDao
import com.example.fitness.core.database.dao.ExerciseDao
import com.example.fitness.core.database.models.ExerciseEntity
import com.example.fitness.domain.models.Exercise
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultExercisesRepository @Inject constructor(
    private val exerciseDao: ExerciseDao,
    private val exerciseCategoryDao: ExerciseCategoryDao
) : ExercisesRepository {

    override suspend fun addExercise(exercise: Exercise) {
        val exerciseEntity = ExerciseEntity(
            exercise.id,
            exercise.categoryId,
            exercise.completedAt
        )
        return exerciseDao.insert(exerciseEntity)
    }

    override suspend fun getExercise(id: Int): Exercise {
        return exerciseDao.get(id).let { exerciseEntity ->
            Exercise(
                exerciseEntity.id,
                exerciseEntity.categoryId,
                exerciseEntity.completedAt
            )
        }
    }

    override fun getExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAll().map { list ->
            list.map { exerciseEntity ->
                Exercise(
                    exerciseEntity.id,
                    exerciseEntity.categoryId,
                    exerciseEntity.completedAt
                )
            }
        }
    }
}