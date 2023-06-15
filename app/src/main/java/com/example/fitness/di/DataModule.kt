package com.example.fitness.di

import com.example.fitness.data.repository.DefaultExercisesRepository
import com.example.fitness.data.repository.ExercisesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindExercisesRepository(
        exercisesRepository: DefaultExercisesRepository
    ): ExercisesRepository
}