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
    fun insert(category: ExerciseCategoryEntity)

    @Query("SELECT * FROM exercise_categories WHERE id = :id")
    fun get(id: Long): ExerciseCategoryEntity

    @Query("SELECT * FROM exercise_categories")
    fun observeAll(): Flow<List<ExerciseCategoryEntity>>

    @Query("SELECT * FROM exercise_categories WHERE id = :id")
    fun observe(id: Long): Flow<ExerciseCategoryEntity>

    @Query("SELECT COUNT(*) FROM exercise_categories")
    fun observeAllCount(): Flow<Int>

    @Query("DELETE FROM exercise_categories WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM exercise_categories")
    fun deleteAll()
}
