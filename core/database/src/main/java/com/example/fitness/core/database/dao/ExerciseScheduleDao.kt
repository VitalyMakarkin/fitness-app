package com.example.fitness.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitness.core.database.models.ExerciseScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(schedule: ExerciseScheduleEntity)

    @Query("SELECT * FROM exercise_schedule WHERE id = :id")
    suspend fun get(id: Int): ExerciseScheduleEntity

    @Query("SELECT * FROM exercise_schedule ORDER BY scheduled_at ASC")
    fun getAll(): Flow<List<ExerciseScheduleEntity>>

    @Query("DELETE FROM exercise_schedule WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM exercise_schedule")
    suspend fun deleteAll()
}