package com.example.fitness.data.repository

import com.example.fitness.domain.models.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseHistoryRepository {

    suspend fun addExercise(exercise: Exercise)

    suspend fun getExercise(id: Int): Exercise

    fun getExercises(): Flow<List<Exercise>>
}