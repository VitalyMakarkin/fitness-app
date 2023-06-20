package com.example.fitness.core.database.di

import com.example.fitness.core.database.AppDatabase
import com.example.fitness.core.database.dao.ExerciseCategoryDao
import com.example.fitness.core.database.dao.ExerciseHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun provideExerciseHistoryDao(
        database: AppDatabase
    ): ExerciseHistoryDao = database.exerciseHistoryDao()

    @Provides
    fun provideExerciseCategoryDao(
        database: AppDatabase
    ): ExerciseCategoryDao = database.exerciseCategoryDao()
}