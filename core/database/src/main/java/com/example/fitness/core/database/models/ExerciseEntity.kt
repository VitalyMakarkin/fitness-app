package com.example.fitness.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises"
)
data class ExerciseEntity(

    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,

    @ColumnInfo(name = COLUMN_CATEGORY_ID)
    val categoryId: Int?,

    @ColumnInfo(name = COLUMN_COMPLETED_AT)
    val completedAt: Long
) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_CATEGORY_ID = "category_id"
        const val COLUMN_COMPLETED_AT = "completed_at"
    }
}