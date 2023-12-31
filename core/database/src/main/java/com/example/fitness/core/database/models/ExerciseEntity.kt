package com.example.fitness.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises"
)
data class ExerciseEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,

    @ColumnInfo(name = COLUMN_NAME)
    val name: String,

    @ColumnInfo(name = COLUMN_EXERCISE_CATEGORY_ID)
    val exerciseCategoryId: Long,

    @ColumnInfo(name = COLUMN_CREATED_AT)
    val createdAt: Long,

    @ColumnInfo(name = COLUMN_COMPLETED_AT)
    val completedAt: Long,

    @ColumnInfo(name = COLUMN_SETS)
    val sets: Int?,

    @ColumnInfo(name = COLUMN_REPS)
    val reps: Int?,

    @ColumnInfo(name = COLUMN_DURATION)
    val duration: Long?,

    @ColumnInfo(name = COLUMN_SCORE)
    val score: Int
) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_EXERCISE_CATEGORY_ID = "exercise_category_id"
        const val COLUMN_CREATED_AT = "created_at"
        const val COLUMN_COMPLETED_AT = "completed_at"
        const val COLUMN_SETS = "sets"
        const val COLUMN_REPS = "reps"
        const val COLUMN_DURATION = "duration"
        const val COLUMN_SCORE = "score"
    }
}
