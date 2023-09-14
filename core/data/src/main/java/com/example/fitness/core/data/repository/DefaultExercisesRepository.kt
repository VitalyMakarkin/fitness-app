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
import com.example.fitness.core.database.models.ExerciseCategoryEntity
import com.example.fitness.core.database.models.ExerciseEntity
import com.example.fitness.core.database.models.ExerciseGroupEntity
import com.example.fitness.core.database.models.ScheduledExerciseEventEntity
import com.example.fitness.core.model.Exercise
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.core.model.ExerciseGroup
import com.example.fitness.core.model.ScheduledExerciseEvent
import com.example.fitness.core.network.api.WgerApi
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultExercisesRepository @Inject constructor(
    private val exerciseCategoryDao: ExerciseCategoryDao,
    private val exerciseGroupDao: ExerciseGroupDao,
    private val exerciseDao: ExerciseDao,
    private val exerciseEventDao: ScheduledExerciseEventDao,
    private val wgerApi: WgerApi
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
            .map { list -> list.map { exerciseEntity -> exerciseEntity.mapToExercise() } }
    }

    override fun observeExercisesCount(): Flow<Int> {
        return exerciseDao.observeAllCount()
    }

    override fun observeScheduledExerciseEvents(): Flow<List<ScheduledExerciseEvent>> {
        return exerciseEventDao.observeAll()
            .map { list -> list.map { event -> event.mapToScheduledExerciseEvent() } }
    }

    override fun observeExerciseCategories(): Flow<List<ExerciseCategory>> {
        return exerciseCategoryDao.observeAll()
            .map { list -> list.map { category -> category.mapToExerciseCategory() } }
    }

    override fun observeExerciseCategory(id: Int): Flow<ExerciseCategory> {
        return exerciseCategoryDao.observe(id)
            .map { category -> category.mapToExerciseCategory() }
    }

    override fun observeExerciseCategoriesCount(): Flow<Int> {
        return exerciseCategoryDao.observeAllCount()
    }

    override suspend fun updateAllCategories() {
        withContext(Dispatchers.IO) {
            val response = wgerApi.getExerciseCategories()

            try {
                response.body()?.let { pageResponse ->
                    exerciseCategoryDao.deleteAll()
                    pageResponse.results.forEach { categoryResponse ->
                        val category = ExerciseCategoryEntity(
                            0,
                            categoryResponse.name,
                            "Base category",
                        )

                        exerciseCategoryDao.insert(category)
                    }
                }

            } catch (error: Throwable) {
                // TODO
            }
        }
    }

    override suspend fun clearAllExerciseCategories() {
        return exerciseCategoryDao.deleteAll()
    }

    override fun observeExerciseGroups(): Flow<List<ExerciseGroup>> {
        return exerciseGroupDao.observeAll()
            .map { list ->
                list.map { group ->
                    group.mapToExerciseGroup()
                }
            }
    }

    override fun observeExerciseGroup(id: Int): Flow<ExerciseGroup> {
        return exerciseGroupDao.observePopulated(id)
            .map { group -> group.mapToExerciseGroup() }
    }

    override fun observeExerciseGroupsCount(): Flow<Int> {
        return exerciseGroupDao.observeAllCount()
    }

    override suspend fun createExerciseCategory(name: String, description: String?) {
        val exerciseCategoryEntity = ExerciseCategoryEntity(
            id = 0,
            name = name,
            description = description
        )
        exerciseCategoryDao.insert(exerciseCategoryEntity)
    }

    override suspend fun createExerciseGroup(name: String) {
        val exerciseGroup = ExerciseGroupEntity(
            id = 0,
            name = name
        )
        exerciseGroupDao.insert(exerciseGroup)
    }

    override suspend fun createScheduledEvent(scheduledAt: Long, exerciseGroupId: Int) {
        val event = ScheduledExerciseEventEntity(
            id = 0,
            scheduledAt = scheduledAt,
            exerciseGroupId = exerciseGroupId
        )
        exerciseEventDao.insert(event)
    }

    override suspend fun saveCompletedExercise(
        name: String,
        exerciseCategoryId: Int,
        createdAt: Long,
        completedAt: Long,
        sets: Int?,
        reps: Int?,
        duration: Long?,
        score: Int
    ) {
        val exerciseEntity = ExerciseEntity(
            id = 0,
            name = name,
            exerciseCategoryId = exerciseCategoryId,
            createdAt = createdAt,
            completedAt = completedAt,
            sets = sets,
            reps = reps,
            duration = duration,
            score = score
        )
        exerciseDao.insert(exerciseEntity)
    }
}