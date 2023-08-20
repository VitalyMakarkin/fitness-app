package com.example.fitness.core.data.repository

import com.example.fitness.core.data.mapper.mapToExercise
import com.example.fitness.core.data.mapper.mapToExerciseCategory
import com.example.fitness.core.data.mapper.mapToExerciseEntity
import com.example.fitness.core.data.mapper.mapToExerciseGroup
import com.example.fitness.core.data.mapper.mapToScheduledExerciseEvent
import com.example.fitness.core.database.dao.ExerciseCategoryDao
import com.example.fitness.core.database.dao.ExerciseDao
import com.example.fitness.core.database.dao.ExerciseGroupDao
import com.example.fitness.core.database.dao.ScheduledExerciseEventDao
import com.example.fitness.core.model.Exercise
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.core.model.ExerciseGroup
import com.example.fitness.core.model.ScheduledExerciseEvent
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

    override fun observeExercises(): Flow<List<Exercise>> {
        return exerciseDao.observeAll()
            .map { list ->
                list.map { exerciseEntity ->
                    exerciseEntity.mapToExercise()
                }
            }
    }

    override fun observeExercisesCount(): Flow<Int> {
        return exerciseDao.observeAllCount()
    }

    override fun observeScheduledExerciseEvents(): Flow<List<ScheduledExerciseEvent>> {
        return exerciseEventDao.observeAll()
            .map { list ->
                list.map { event ->
                    event.mapToScheduledExerciseEvent()
                }
            }
    }

    override fun observeExerciseCategories(): Flow<List<ExerciseCategory>> {
        return exerciseCategoryDao.observeAll()
            .map { list ->
                list.map { category ->
                    category.mapToExerciseCategory()
                }
            }
    }

    override fun observeExerciseCategoriesCount(): Flow<Int> {
        return exerciseCategoryDao.observeAllCount()
    }

    override fun observeExerciseGroups(): Flow<List<ExerciseGroup>> {
        return exerciseGroupDao.observeAll()
            .map { list ->
                list.map { group ->
                    group.mapToExerciseGroup()
                }
            }
    }

    override fun observeExerciseGroupsCount(): Flow<Int> {
        return exerciseGroupDao.observeAllCount()
    }
}