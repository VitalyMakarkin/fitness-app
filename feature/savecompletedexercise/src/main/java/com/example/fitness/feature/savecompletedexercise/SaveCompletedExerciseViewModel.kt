package com.example.fitness.feature.savecompletedexercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SaveCompletedExerciseViewModel @Inject constructor(
    private val interactor: SaveCompletedExerciseInteractor
) : ViewModel() {

    val saveCompletedExerciseUiState: StateFlow<SaveCompletedExerciseUiState> =
        saveCompletedExerciseUiState(
            interactor = interactor
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SaveCompletedExerciseUiState.Loading
        )

    private fun saveCompletedExerciseUiState(interactor: SaveCompletedExerciseInteractor): Flow<SaveCompletedExerciseUiState> {
        return interactor.observeExerciseCategories()
            .map { list -> SaveCompletedExerciseUiState.Success(list) }
    }

    fun saveExercise() {
        viewModelScope.launch {
            interactor.saveExercise()
        }
    }
}