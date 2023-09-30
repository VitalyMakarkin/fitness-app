package com.example.fitness.core.data.repository

import com.example.fitness.core.model.Exercise
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.core.model.ExerciseGroup
import com.example.fitness.core.model.ExerciseGroupItem
import com.example.fitness.core.model.ScheduledExerciseEvent
import kotlinx.coroutines.flow.Flow

interface ExercisesRepository {

    suspend fun addExercise(exercise: Exercise)

    suspend fun getExercise(id: Long): Exercise

    fun observeExercises(): Flow<List<Exercise>>

    fun observeExercisesCount(): Flow<Int>

    fun observeExercisesAverageScore(lastExerciseCount: Int): Flow<Double>

    fun observeScheduledExerciseEvents(): Flow<List<ScheduledExerciseEvent>>

    fun observeExerciseCategories(): Flow<List<ExerciseCategory>>

    fun observeExerciseCategory(id: Long): Flow<ExerciseCategory>

    fun observeExerciseCategoriesCount(): Flow<Int>

    suspend fun updateAllCategories()

    suspend fun clearAllExerciseCategories()

    fun observeExerciseGroups(): Flow<List<ExerciseGroup>>

    fun observeExerciseGroup(id: Long): Flow<ExerciseGroup>

    fun observeExerciseGroupsCount(): Flow<Int>

    suspend fun createExerciseCategory(name: String, description: String?)

    suspend fun createExerciseGroup(name: String, exercises: List<ExerciseGroupItem>)

    suspend fun createScheduledEvent(scheduledAt: Long, exerciseGroupId: Long)

    suspend fun saveCompletedExercise(
        name: String,
        exerciseCategoryId: Long,
        createdAt: Long,
        completedAt: Long,
        sets: Int? = null,
        reps: Int? = null,
        duration: Long? = null,
        score: Int
    )
}