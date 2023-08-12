package com.example.fitness.core.database.di

import com.example.fitness.core.database.AppDatabase
import com.example.fitness.core.database.dao.ExerciseCategoryDao
import com.example.fitness.core.database.dao.ExerciseDao
import com.example.fitness.core.database.dao.ExerciseGroupDao
import com.example.fitness.core.database.dao.ScheduledExerciseEventDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun provideExerciseCategoryDao(
        database: AppDatabase
    ): ExerciseCategoryDao = database.exerciseCategoryDao()

    @Provides
    fun provideExerciseDao(
        database: AppDatabase
    ): ExerciseDao = database.exerciseDao()

    @Provides
    fun provideExerciseGroupDao(
        database: AppDatabase
    ): ExerciseGroupDao = database.exerciseGroupDao()

    @Provides
    fun provideScheduledExerciseEventDao(
        database: AppDatabase
    ): ScheduledExerciseEventDao = database.scheduledExerciseEventDao()
}