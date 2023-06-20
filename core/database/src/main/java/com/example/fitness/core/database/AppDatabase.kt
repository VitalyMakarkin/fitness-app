package com.example.fitness.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitness.core.database.dao.ExerciseCategoryDao
import com.example.fitness.core.database.dao.ExerciseHistoryDao
import com.example.fitness.core.database.models.ExerciseCategoryEntity
import com.example.fitness.core.database.models.ExerciseHistoryEntity

@Database(
    entities = [
        ExerciseCategoryEntity::class,
        ExerciseHistoryEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseCategoryDao(): ExerciseCategoryDao
    abstract fun exerciseHistoryDao(): ExerciseHistoryDao

}