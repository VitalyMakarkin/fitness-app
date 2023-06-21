package com.example.fitness.di

import com.example.fitness.data.repository.DefaultExerciseHistoryRepository
import com.example.fitness.data.repository.ExerciseHistoryRepository
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