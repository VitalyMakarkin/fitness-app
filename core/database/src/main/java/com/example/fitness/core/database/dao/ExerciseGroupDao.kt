package com.example.fitness.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.fitness.core.database.models.ExerciseGroupEntity
import com.example.fitness.core.database.models.ExerciseGroupItemEntity
import com.example.fitness.core.database.models.PopulatedExerciseGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseGroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(group: ExerciseGroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ExerciseGroupItemEntity)

    @Transaction
    @Query("SELECT * FROM exercise_groups")
    fun getAll(): Flow<List<PopulatedExerciseGroup>>

    @Transaction
    @Query("SELECT * FROM exercise_groups WHERE id = :id")
    fun getPopulatedGroup(id: Int): Flow<PopulatedExerciseGroup>

    @Query("DELETE FROM exercise_groups WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM exercise_group_items WHERE id = :id")
    suspend fun deleteItem(id: Int)
}