package com.example.fitness.core.data.repository

import com.example.fitness.core.data.mapper.mapToExercise
import com.example.fitness.core.data.mapper.mapToExerciseEntity
import com.example.fitness.core.database.dao.ExerciseDao
import com.example.fitness.core.model.Exercise
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultExerciseHistoryRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ExerciseHistoryRepository {

    override suspend fun addExercise(exercise: Exercise) {
        return exerciseDao.insert(
            exercise = exercise.mapToExerciseEntity()
        )
    }

    override suspend fun getExercise(id: Int): Exercise {
        return exerciseDao.get(id).mapToExercise()
    }

    override fun getExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAll().map { list ->
            list.map { exerciseEntity ->
                exerciseEntity.mapToExercise()
            }
        }
    }
}