package com.example.fitness.core.data.repository

import com.example.fitness.core.model.Exercise
import kotlinx.coroutines.flow.Flow

interface ExercisesRepository {

    suspend fun addExercise(exercise: Exercise)

    suspend fun getExercise(id: Int): Exercise

    fun getExercises(): Flow<List<Exercise>>
}