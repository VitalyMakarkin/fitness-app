package com.example.fitness.core.database.di

import com.example.fitness.core.database.AppDatabase
import com.example.fitness.core.database.dao.ExerciseCategoryDao
import com.example.fitness.core.database.dao.ExerciseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun provideExerciseDao(
        database: AppDatabase
    ): ExerciseDao = database.exerciseDao()

    @Provides
    fun provideExerciseCategoryDao(
        database: AppDatabase
    ): ExerciseCategoryDao = database.exerciseCategoryDao()
}