package com.example.fitness.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitness.core.database.models.ExerciseCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: ExerciseCategoryEntity)

    @Query("SELECT * FROM exercise_categories WHERE id = :id")
    suspend fun get(id: Int): ExerciseCategoryEntity

    @Query("SELECT * FROM exercise_categories")
    fun observeAll(): Flow<List<ExerciseCategoryEntity>>

    @Query("DELETE FROM exercise_categories WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM exercise_categories")
    suspend fun deleteAll()
}