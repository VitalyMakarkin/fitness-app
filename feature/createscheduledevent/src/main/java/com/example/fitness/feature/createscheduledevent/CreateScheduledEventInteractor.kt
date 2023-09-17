package com.example.fitness.feature.createscheduledevent

import com.example.fitness.core.data.repository.ExercisesRepository
import com.example.fitness.core.model.ExerciseGroup
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class CreateScheduledEventInteractor @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) {

    suspend fun createScheduledEvent(scheduledAt: Long, exerciseGroupId: Long) {
        exercisesRepository.createScheduledEvent(scheduledAt, exerciseGroupId)
    }

    fun observeExerciseGroups(): Flow<List<ExerciseGroup>> {
        return exercisesRepository.observeExerciseGroups()
    }

    fun observeExerciseGroup(id: Long): Flow<ExerciseGroup> {
        return exercisesRepository.observeExerciseGroup(id)
    }
}