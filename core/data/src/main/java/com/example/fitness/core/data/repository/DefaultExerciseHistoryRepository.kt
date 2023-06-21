package com.example.fitness.core.data.repository

import com.example.fitness.core.database.dao.ExerciseHistoryDao
import com.example.fitness.core.model.Exercise
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultExerciseHistoryRepository @Inject constructor(
    private val exerciseHistoryDao: ExerciseHistoryDao
) : ExerciseHistoryRepository {

    override suspend fun addExercise(exercise: Exercise) {
//        val exerciseEntity = ExerciseHistoryEntity(
//            exercise.id,
//            exercise.categoryId,
//            exercise.completedAt
//        )
//        return exerciseHistoryDao.insert(exerciseEntity)
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