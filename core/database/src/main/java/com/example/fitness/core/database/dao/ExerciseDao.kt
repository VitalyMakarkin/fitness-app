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
    fun insert(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercises WHERE id = :id")
    fun get(id: Int): ExerciseEntity

    @Query("SELECT * FROM exercises ORDER BY completed_at DESC")
    fun observeAll(): Flow<List<ExerciseEntity>>

    @Query("SELECT COUNT(*) FROM exercises")
    fun observeAllCount(): Flow<Int>

    @Query("SELECT * FROM exercises WHERE exercise_category_id =:id ORDER BY completed_at DESC")
    fun getAllByCategoryId(id: Int): List<ExerciseEntity>

    @Query("DELETE FROM exercises WHERE id = :id")
    fun delete(id: Int)

    @Query("DELETE FROM exercises")
    fun deleteAll()
}