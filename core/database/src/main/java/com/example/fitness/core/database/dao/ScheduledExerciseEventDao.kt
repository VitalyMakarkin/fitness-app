package com.example.fitness.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.fitness.core.database.models.PopulatedScheduledExerciseEvent
import com.example.fitness.core.database.models.ScheduledExerciseEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduledExerciseEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(schedule: ScheduledExerciseEventEntity)

    @Transaction
    @Query("SELECT * FROM scheduled_exercise_events WHERE id = :id")
    fun get(id: Long): PopulatedScheduledExerciseEvent

    @Transaction
    @Query("SELECT * FROM scheduled_exercise_events ORDER BY scheduled_at ASC")
    fun observeAll(): Flow<List<PopulatedScheduledExerciseEvent>>

    @Query("DELETE FROM scheduled_exercise_events WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM scheduled_exercise_events")
    fun deleteAll()
}
