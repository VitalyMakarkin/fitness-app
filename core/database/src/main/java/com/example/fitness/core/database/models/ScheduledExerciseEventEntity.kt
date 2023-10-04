package com.example.fitness.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "scheduled_exercise_events"
)
data class ScheduledExerciseEventEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,

    @ColumnInfo(name = COLUMN_SCHEDULED_AT)
    val scheduledAt: Long,

    @ColumnInfo(name = COLUMN_EXERCISE_GROUP_ID)
    val exerciseGroupId: Long
) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_SCHEDULED_AT = "scheduled_at"
        const val COLUMN_EXERCISE_GROUP_ID = "exercise_group_id"
    }
}
