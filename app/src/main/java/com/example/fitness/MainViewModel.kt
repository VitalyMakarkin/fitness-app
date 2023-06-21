package com.example.fitness

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.core.data.repository.ExerciseHistoryRepository
import com.example.fitness.core.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ExerciseHistoryRepository
) : ViewModel() {

    val mainUiState: StateFlow<MainUiState> = exercisesUiState(
        repository = repository
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainUiState.Loading
    )

    fun addExercise() {
        viewModelScope.launch {
            val currentTimestamp = System.currentTimeMillis()
            val newExercise = Exercise(
                0,
                0,
                currentTimestamp,
                currentTimestamp,
                null,
                null,
                null,
                0
            )
            repository.addExercise(newExercise)
        }
    }
}

private fun exercisesUiState(repository: ExerciseHistoryRepository): Flow<MainUiState> {
    return repository.getExercises().map { exercises ->
        MainUiState.Success(exercises)
    }
}

sealed interface MainUiState {
    data class Success(val completedExercises: List<Exercise>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}