package com.example.fitness.feature.createscheduledevent.dialog.exercisegroupselection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.feature.createscheduledevent.CreateScheduledEventInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ExerciseGroupSelectionViewModel @Inject constructor(
    private val interactor: CreateScheduledEventInteractor
) : ViewModel() {

    val uiState: StateFlow<ExerciseGroupSelectionUiState> =
        exerciseGroupSelectionUiState(
            interactor = interactor
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ExerciseGroupSelectionUiState.Loading
        )

    private fun exerciseGroupSelectionUiState(interactor: CreateScheduledEventInteractor): Flow<ExerciseGroupSelectionUiState> {
        return interactor.observeExerciseGroups()
            .map { list -> ExerciseGroupSelectionUiState.Success(list) }
    }
}
