package com.example.fitness.feature.exercisehistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.core.data.repository.ExercisesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ExerciseHistoryViewModel @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) : ViewModel() {

    val exerciseHistoryUiState: StateFlow<ExerciseHistoryUiState> = exerciseHistoryUiState(
        repository = exercisesRepository
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ExerciseHistoryUiState.Loading
    )

    private fun exerciseHistoryUiState(repository: ExercisesRepository): Flow<ExerciseHistoryUiState> {
        return repository.getExercises().map { exercises ->
            ExerciseHistoryUiState.Success(exercises)
        }
    }
}