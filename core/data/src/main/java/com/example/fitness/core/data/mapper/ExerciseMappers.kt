package com.example.fitness.core.data.mapper

import com.example.fitness.core.database.models.ExerciseCategoryEntity
import com.example.fitness.core.database.models.ExerciseEntity
import com.example.fitness.core.database.models.ExerciseGroupItemEntity
import com.example.fitness.core.database.models.PopulatedExerciseGroup
import com.example.fitness.core.database.models.PopulatedScheduledExerciseEvent
import com.example.fitness.core.model.Exercise
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.core.model.ExerciseGroup
import com.example.fitness.core.model.ExerciseGroupItem
import com.example.fitness.core.model.ScheduledExerciseEvent

internal fun Exercise.mapToExerciseEntity() = with(this) {
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

internal fun ExerciseEntity.mapToExercise() = with(this) {
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

internal fun ExerciseGroupItemEntity.mapToExerciseGroupItem() = with(this) {
    ExerciseGroupItem(
        id,
        name,
        exerciseCategoryId,
        sets,
        reps,
        duration
    )
}

internal fun PopulatedExerciseGroup.mapToExerciseGroup() = with(this) {
    ExerciseGroup(
        group.id,
        group.name,
        exercises = items.map { it.mapToExerciseGroupItem() }
    )
}

internal fun PopulatedScheduledExerciseEvent.mapToScheduledExerciseEvent() = with(this) {
    ScheduledExerciseEvent(
        event.id,
        event.scheduledAt,
        exerciseGroup.mapToExerciseGroup()
    )
}

internal fun ExerciseCategoryEntity.RequiredState.mapToExerciseCategoryRequiredState() = when (this) {
    ExerciseCategoryEntity.RequiredState.REQUIRED -> ExerciseCategory.RequiredState.REQUIRED
    ExerciseCategoryEntity.RequiredState.OPTIONAL -> ExerciseCategory.RequiredState.OPTIONAL
    else -> ExerciseCategory.RequiredState.NONE
}

internal fun ExerciseCategoryEntity.mapToExerciseCategory() = with(this) {
    ExerciseCategory(
        id,
        name,
        description,
        containsSets.mapToExerciseCategoryRequiredState(),
        containsReps.mapToExerciseCategoryRequiredState(),
        containsDuration.mapToExerciseCategoryRequiredState()
    )
}