package com.example.fitness.feature.exercisegroups

import com.example.fitness.core.data.repository.ExercisesRepository
import com.example.fitness.core.model.ExerciseGroup
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ExerciseGroupsInteractor @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) {

    fun observeExerciseGroups() : Flow<List<ExerciseGroup>> {
        return exercisesRepository.observeExerciseGroups()
    }

    suspend fun deleteExerciseGroup(id: Long) {
        return exercisesRepository.deleteExerciseGroup(id)
    }
}