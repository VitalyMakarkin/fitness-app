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
    fun get(id: Long): ExerciseEntity

    @Query("SELECT * FROM exercises ORDER BY completed_at DESC")
    fun observeAll(): Flow<List<ExerciseEntity>>

    @Query("SELECT COUNT(*) FROM exercises")
    fun observeAllCount(): Flow<Int>

    @Query("SELECT AVG(score) FROM exercises ORDER BY completed_at DESC LIMIT :exercisesCount")
    fun observeLastExercisesAverageScore(exercisesCount: Int): Flow<Double?>

    @Query("SELECT * FROM exercises WHERE exercise_category_id =:id ORDER BY completed_at DESC")
    fun getAllByCategoryId(id: Long): List<ExerciseEntity>

    @Query("DELETE FROM exercises WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM exercises")
    fun deleteAll()
}