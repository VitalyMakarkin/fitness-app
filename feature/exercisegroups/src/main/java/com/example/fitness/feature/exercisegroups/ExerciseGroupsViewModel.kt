package com.example.fitness.feature.exercisegroups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.feature.exercisegroups.model.mapToExerciseGroupUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ExerciseGroupsViewModel @Inject constructor(
    private val interactor: ExerciseGroupsInteractor
) : ViewModel() {

    val exerciseGroupsUiState: StateFlow<ExerciseGroupsUiState> = exerciseGroupsUiState(
        interactor = interactor
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ExerciseGroupsUiState.Loading
    )

    private fun exerciseGroupsUiState(interactor: ExerciseGroupsInteractor): Flow<ExerciseGroupsUiState> {
        return interactor.observeExerciseGroups()
            .map { list ->
                ExerciseGroupsUiState.Success(
                    exerciseGroups = list.map { it.mapToExerciseGroupUI() }
                )
            }
    }
}