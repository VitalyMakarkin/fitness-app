package com.example.fitness.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercise_categories"
)
data class ExerciseCategoryEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,

    @ColumnInfo(name = COLUMN_NAME)
    val name: String,

    @ColumnInfo(name = COLUMN_DESCRIPTION)
    val description: String?,

    @ColumnInfo(name = COLUMN_CONTAINS_SETS)
    val containsSets: RequiredState,

    @ColumnInfo(name = COLUMN_CONTAINS_REPS)
    val containsReps: RequiredState,

    @ColumnInfo(name = COLUMN_CONTAINS_DURATION)
    val containsDuration: RequiredState
) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_CONTAINS_SETS = "contains_sets"
        const val COLUMN_CONTAINS_REPS = "contains_reps"
        const val COLUMN_CONTAINS_DURATION = "contains_duration"
    }

    enum class RequiredState {
        NONE,
        OPTIONAL,
        REQUIRED
    }
}