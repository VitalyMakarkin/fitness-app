package com.example.fitness.core.data.repository

import com.example.fitness.core.model.Exercise
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.core.model.ExerciseGroup
import com.example.fitness.core.model.ScheduledExerciseEvent
import kotlinx.coroutines.flow.Flow

interface ExercisesRepository {

    suspend fun addExercise(exercise: Exercise)

    suspend fun getExercise(id: Int): Exercise

    fun observeExercises(): Flow<List<Exercise>>

    fun observeExercisesCount(): Flow<Int>

    fun observeScheduledExerciseEvents(): Flow<List<ScheduledExerciseEvent>>

    fun observeExerciseCategories(): Flow<List<ExerciseCategory>>

    fun observeExerciseCategoriesCount(): Flow<Int>

    fun observeExerciseGroups(): Flow<List<ExerciseGroup>>

    fun observeExerciseGroupsCount(): Flow<Int>

    suspend fun createExerciseCategory()
}