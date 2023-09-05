package com.example.fitness.core.data.mapper

import com.example.fitness.core.database.models.ExerciseCategoryEntity
import com.example.fitness.core.database.models.ExerciseEntity
import com.example.fitness.core.database.models.ExerciseGroupEntity
import com.example.fitness.core.database.models.ExerciseGroupItemEntity
import com.example.fitness.core.database.models.PopulatedExerciseGroup
import com.example.fitness.core.database.models.PopulatedScheduledExerciseEvent
import com.example.fitness.core.database.models.ScheduledExerciseEventEntity
import com.example.fitness.core.model.Exercise
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.core.model.ExerciseGroup
import com.example.fitness.core.model.ScheduledExerciseEvent

fun Exercise.mapToExerciseEntity() = with(this) {
    ExerciseEntity(
        id,
        name,
        exerciseCategoryId,
        createdAt,
        completedAt,
        sets,
        reps,
        duration,
        score
    )
}

fun ExerciseEntity.mapToExercise() = with(this) {
    Exercise(
        id,
        name,
        exerciseCategoryId,
        createdAt,
        completedAt,
        sets,
        reps,
        duration,
        score
    )
}

fun ExerciseGroup.mapToExerciseGroupEntity() = with(this) {
    ExerciseGroupEntity(id, name)
}

fun ExerciseGroupItemEntity.mapToExercise() = with(this) {
    Exercise(
        id,
        "", // TODO
        exerciseCategoryId,
        createdAt = 0,
        completedAt = 0,
        sets,
        reps,
        duration,
        score = 0
    )
}

fun PopulatedExerciseGroup.mapToExerciseGroup() = with(this) {
    ExerciseGroup(
        group.id,
        group.name,
        exercises = items.map { it.mapToExercise() }
    )
}

fun ScheduledExerciseEvent.mapToScheduledExerciseEventEntity() = with(this) {
    ScheduledExerciseEventEntity(
        id,
        scheduledAt,
        exerciseGroup.id
    )
}

fun PopulatedScheduledExerciseEvent.mapToScheduledExerciseEvent() = with(this) {
    ScheduledExerciseEvent(
        event.id,
        event.scheduledAt,
        exerciseGroup.mapToExerciseGroup()
    )
}

fun ExerciseCategoryEntity.RequiredState.mapToExerciseCategoryRequiredState() = when (this) {
    ExerciseCategoryEntity.RequiredState.REQUIRED -> ExerciseCategory.RequiredState.REQUIRED
    ExerciseCategoryEntity.RequiredState.OPTIONAL -> ExerciseCategory.RequiredState.OPTIONAL
    else -> ExerciseCategory.RequiredState.NONE
}

fun ExerciseCategoryEntity.mapToExerciseCategory() = with(this) {
    ExerciseCategory(
        id,
        name,
        description,
        containsSets.mapToExerciseCategoryRequiredState(),
        containsReps.mapToExerciseCategoryRequiredState(),
        containsDuration.mapToExerciseCategoryRequiredState()
    )
}