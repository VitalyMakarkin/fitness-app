package com.example.fitness.core.database.models

import androidx.room.Embedded
import androidx.room.Relation

data class PopulatedExerciseGroup(

    @Embedded
    val group: ExerciseGroupEntity,

    @Relation(
        parentColumn = ExerciseGroupEntity.COLUMN_ID,
        entityColumn = ExerciseGroupItemEntity.COLUMN_EXERCISE_GROUP_ID
    )
    val items: List<ExerciseGroupItemEntity>
)
