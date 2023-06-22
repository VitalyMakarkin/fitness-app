package com.example.fitness.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitness.core.database.models.ScheduledExerciseEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduledExerciseEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(schedule: ScheduledExerciseEventEntity)

    @Query("SELECT * FROM scheduled_exercise_events WHERE id = :id")
    suspend fun get(id: Int): ScheduledExerciseEventEntity

    @Query("SELECT * FROM scheduled_exercise_events ORDER BY scheduled_at ASC")
    fun getAll(): Flow<List<ScheduledExerciseEventEntity>>

    @Query("DELETE FROM scheduled_exercise_events WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM scheduled_exercise_events")
    suspend fun deleteAll()
}