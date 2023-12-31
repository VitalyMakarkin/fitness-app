package com.example.fitness.feature.exercisesettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ExerciseSettingsViewModel @Inject constructor(
    private val interactor: ExerciseSettingsInteractor
) : ViewModel() {

    val uiState: StateFlow<ExerciseSettingsUiState> = exerciseSettingsUiState(
        interactor = interactor
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ExerciseSettingsUiState.Loading
    )

    private fun exerciseSettingsUiState(interactor: ExerciseSettingsInteractor): Flow<ExerciseSettingsUiState> {
        return combine(
            interactor.observeExercisesCount(),
            interactor.observeExerciseCategoriesCount(),
            interactor.observeExerciseGroupsCount()
        ) { exercisesCount, exerciseCategoriesCount, exerciseGroupsCount ->
            ExerciseSettingsUiState.Success(
                activitiesCount = exercisesCount,
                exerciseCategoriesCount = exerciseCategoriesCount,
                exerciseGroupsCount = exerciseGroupsCount
            )
        }
    }
}
