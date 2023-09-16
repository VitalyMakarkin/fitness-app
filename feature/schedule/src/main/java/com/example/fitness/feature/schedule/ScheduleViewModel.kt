package com.example.fitness.feature.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.feature.schedule.model.mapToScheduledEventUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val interactor: ScheduleInteractor
) : ViewModel() {

    val uiState: StateFlow<ScheduleUiState> = scheduleUiState(
        interactor = interactor
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ScheduleUiState.Loading
    )

    private fun scheduleUiState(interactor: ScheduleInteractor): Flow<ScheduleUiState> {
        return interactor.observeEvents()
            .map { events ->
                ScheduleUiState.Success(
                    events = events.map { it.mapToScheduledEventUI() }
                )
            }
    }
}