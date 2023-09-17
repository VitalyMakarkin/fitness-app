package com.example.fitness.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercise_groups"
)
data class ExerciseGroupEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,

    @ColumnInfo(name = COLUMN_NAME)
    val name: String
) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
    }
}