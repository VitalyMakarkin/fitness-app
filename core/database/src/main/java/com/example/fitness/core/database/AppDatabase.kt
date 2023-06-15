package com.example.fitness.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitness.core.database.dao.ExerciseCategoryDao
import com.example.fitness.core.database.dao.ExerciseDao
import com.example.fitness.core.database.models.ExerciseCategoryEntity
import com.example.fitness.core.database.models.ExerciseEntity

@Database(
    entities = [
        ExerciseEntity::class,
        ExerciseCategoryEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun exerciseCategoryDao(): ExerciseCategoryDao
}