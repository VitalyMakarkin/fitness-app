package com.example.fitness.data.repository

import com.example.fitness.core.database.dao.ExerciseCategoryDao
import com.example.fitness.core.database.dao.ExerciseHistoryDao
import com.example.fitness.domain.models.Exercise
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultExercisesRepository @Inject constructor(
    private val exerciseHistoryDao: ExerciseHistoryDao,
    private val exerciseCategoryDao: ExerciseCategoryDao
) : ExercisesRepository {

    override suspend fun addExercise(exercise: Exercise) {
//        val exerciseEntity = ExerciseHistoryEntity(
//            exercise.id,
//            exercise.categoryId,
//            exercise.completedAt
//        )
//        return exerciseHistoryDao.insert(exerciseEntity)
        TODO("Implement")
    }

    override suspend fun getExercise(id: Int): Exercise {
        return exerciseHistoryDao.get(id).let { exerciseEntity ->
            Exercise(
                exerciseEntity.id,
                exerciseEntity.categoryId,
                exerciseEntity.completedAt
            )
        }
    }

    override fun getExercises(): Flow<List<Exercise>> {
        return exerciseHistoryDao.getAll().map { list ->
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