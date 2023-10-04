package com.example.fitness.feature.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.zip

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val interactor: ActivityInteractor
) : ViewModel() {

    val uiState: StateFlow<ActivityUiState> = activityUiState(
        interactor = interactor
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ActivityUiState.Loading
    )

    private fun activityUiState(interactor: ActivityInteractor): Flow<ActivityUiState> {
        return interactor.observeExercisesCount()
            .zip(interactor.observeExercisesAverageScore(lastExerciseCount = 10)) { count, average ->
                ActivityUiState.Success(
                    activitiesCount = count,
                    averageScore = average
                )
            }
    }
}
