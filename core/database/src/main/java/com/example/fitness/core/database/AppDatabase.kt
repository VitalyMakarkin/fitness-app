package com.example.fitness.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitness.core.database.dao.ExerciseCategoryDao
import com.example.fitness.core.database.dao.ExerciseGroupDao
import com.example.fitness.core.database.dao.ExerciseDao
import com.example.fitness.core.database.dao.ScheduledExerciseEventDao
import com.example.fitness.core.database.models.ExerciseCategoryEntity
import com.example.fitness.core.database.models.ExerciseGroupEntity
import com.example.fitness.core.database.models.ExerciseGroupItemEntity
import com.example.fitness.core.database.models.ExerciseEntity
import com.example.fitness.core.database.models.ScheduledExerciseEventEntity

@Database(
    entities = [
        ExerciseCategoryEntity::class,
        ExerciseEntity::class,
        ExerciseGroupEntity::class,
        ExerciseGroupItemEntity::class,
        ScheduledExerciseEventEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseCategoryDao(): ExerciseCategoryDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun exerciseGroupDao(): ExerciseGroupDao
    abstract fun scheduledExerciseEventDao(): ScheduledExerciseEventDao
}