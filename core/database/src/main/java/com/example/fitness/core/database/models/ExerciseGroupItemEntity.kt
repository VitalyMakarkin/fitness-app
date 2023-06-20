package com.example.fitness.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercise_group_items"
)
data class ExerciseGroupItemEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,

    @ColumnInfo(name = COLUMN_EXERCISE_GROUP_ID)
    val exerciseGroupId: Int,

    @ColumnInfo(name = COLUMN_EXERCISE_CATEGORY_ID)
    val exerciseCategoryId: Int,

    @ColumnInfo(name = COLUMN_SETS)
    val sets: Int?,

    @ColumnInfo(name = COLUMN_REPS)
    val reps: Int?,

    @ColumnInfo(name = COLUMN_DURATION)
    val duration: Long?
) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_EXERCISE_GROUP_ID = "exercise_group_id"
        const val COLUMN_EXERCISE_CATEGORY_ID = "exercise_category_id"
        const val COLUMN_SETS = "sets"
        const val COLUMN_REPS = "reps"
        const val COLUMN_DURATION = "duration"
    }
}