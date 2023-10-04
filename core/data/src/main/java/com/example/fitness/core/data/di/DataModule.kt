package com.example.fitness.core.data.di

import com.example.fitness.core.data.repository.DefaultExercisesRepository
import com.example.fitness.core.data.repository.ExercisesRepository
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
