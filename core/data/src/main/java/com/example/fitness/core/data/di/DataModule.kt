package com.example.fitness.core.data.di

import com.example.fitness.core.data.repository.DefaultExerciseHistoryRepository
import com.example.fitness.core.data.repository.ExerciseHistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindExercisesRepository(
        exercisesRepository: DefaultExerciseHistoryRepository
    ): ExerciseHistoryRepository
}