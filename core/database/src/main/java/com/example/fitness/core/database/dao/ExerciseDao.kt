package com.example.fitness.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitness.core.database.models.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun get(id: Int): ExerciseEntity

    @Query("SELECT * FROM exercises ORDER BY completed_at DESC")
    fun getAll(): Flow<List<ExerciseEntity>>

    @Query("SELECT COUNT(*) FROM exercises")
    fun getAllCount(): Flow<Int>

    @Query("SELECT * FROM exercises WHERE exercise_category_id =:id ORDER BY completed_at DESC")
    suspend fun getAllByCategoryId(id: Int): List<ExerciseEntity>

    @Query("DELETE FROM exercises WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM exercises")
    suspend fun deleteAll()
}