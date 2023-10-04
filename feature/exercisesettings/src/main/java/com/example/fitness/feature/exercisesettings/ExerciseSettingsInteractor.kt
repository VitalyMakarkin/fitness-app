package com.example.fitness.feature.exercisesettings

import com.example.fitness.core.data.repository.ExercisesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ExerciseSettingsInteractor @Inject constructor(
    private val exerciseRepository: ExercisesRepository
){

    fun observeExercisesCount(): Flow<Int> {
        return exerciseRepository.observeExercisesCount()
    }

    fun observeExerciseCategoriesCount(): Flow<Int> {
        return exerciseRepository.observeExerciseCategoriesCount()
    }

    fun observeExerciseGroupsCount(): Flow<Int> {
        return exerciseRepository.observeExerciseGroupsCount()
    }
}
