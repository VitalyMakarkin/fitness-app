package com.example.fitness.core.data.repository

import com.example.fitness.core.data.mapper.mapToExercise
import com.example.fitness.core.data.mapper.mapToExerciseEntity
import com.example.fitness.core.database.dao.ExerciseCategoryDao
import com.example.fitness.core.database.dao.ExerciseDao
import com.example.fitness.core.database.dao.ExerciseGroupDao
import com.example.fitness.core.database.dao.ScheduledExerciseEventDao
import com.example.fitness.core.model.Exercise
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultExercisesRepository @Inject constructor(
    private val exerciseCategoryDao: ExerciseCategoryDao,
    private val exerciseGroupDao: ExerciseGroupDao,
    private val exerciseDao: ExerciseDao,
    private val exerciseEventDao: ScheduledExerciseEventDao
) : ExercisesRepository {

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

    override fun getExercisesCount(): Flow<Int> {
        return exerciseDao.getAllCount()
    }
}