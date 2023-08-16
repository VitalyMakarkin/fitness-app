package com.example.fitness.core.database.models

import androidx.room.Embedded
import androidx.room.Relation

data class PopulatedScheduledExerciseEvent(

    @Embedded
    val event: ScheduledExerciseEventEntity,

    @Relation(
        parentColumn = ScheduledExerciseEventEntity.COLUMN_EXERCISE_GROUP_ID,
        entityColumn = ExerciseGroupEntity.COLUMN_ID,
        entity = ExerciseGroupEntity::class
    )
    val exerciseGroup: PopulatedExerciseGroup
)