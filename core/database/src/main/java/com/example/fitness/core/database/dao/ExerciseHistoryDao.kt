package com.example.fitness.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitness.core.database.models.ExerciseHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: ExerciseHistoryEntity)

    @Query("SELECT * FROM exercise_history WHERE id = :id")
    suspend fun get(id: Int): ExerciseHistoryEntity

    @Query("SELECT * FROM exercise_history ORDER BY completed_at DESC")
    fun getAll(): Flow<List<ExerciseHistoryEntity>>

    @Query("SELECT * FROM exercise_history WHERE category_id =:id ORDER BY completed_at DESC")
    suspend fun getAllByCategoryId(id: Int): List<ExerciseHistoryEntity>

    @Query("DELETE FROM exercise_history WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM exercise_history")
    suspend fun deleteAll()
}