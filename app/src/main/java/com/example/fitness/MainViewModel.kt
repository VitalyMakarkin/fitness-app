package com.example.fitness

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.data.repository.DefaultExercisesRepository
import com.example.fitness.domain.models.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DefaultExercisesRepository
) : ViewModel() {

    val mainUiState: StateFlow<MainUiState> = completedExercisesUiState(
        repository = repository
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainUiState.Loading
    )
}

private fun completedExercisesUiState(repository: DefaultExercisesRepository): Flow<MainUiState> {
    return repository.getExercises().map { exercises ->
        MainUiState.Success(exercises)
    }
}

sealed interface MainUiState {
    data class Success(val completedExercises: List<Exercise>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}