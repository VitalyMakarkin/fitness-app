package com.example.fitness.feature.schedule

import com.example.fitness.core.data.repository.ExercisesRepository
import com.example.fitness.core.model.ScheduledExerciseEvent
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ScheduleInteractor @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) {

    fun observeEvents(): Flow<List<ScheduledExerciseEvent>> {
        return exercisesRepository.observeScheduledExerciseEvents()
    }
}