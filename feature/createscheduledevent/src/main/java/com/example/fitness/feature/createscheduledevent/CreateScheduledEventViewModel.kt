package com.example.fitness.feature.createscheduledevent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.core.model.ExerciseGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val EVENT_SCHEDULED_AT = "createScheduledEventScheduledAt"
private const val EVENT_EXERCISE_GROUP_ID = "createScheduledEventExerciseGroupId"

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class CreateScheduledEventViewModel @Inject constructor(
    private val interactor: CreateScheduledEventInteractor,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val uiState: StateFlow<CreateScheduledEventUiState> =
        createScheduledEventUiState(
            interactor = interactor
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CreateScheduledEventUiState.Loading
        )

    var shouldNavigateBack by mutableStateOf(false)

    private fun createScheduledEventUiState(interactor: CreateScheduledEventInteractor): Flow<CreateScheduledEventUiState> {
        return savedStateHandle.getStateFlow(EVENT_SCHEDULED_AT, -1L)
            .combine(
                savedStateHandle.getStateFlow(EVENT_EXERCISE_GROUP_ID, -1)
            ) { scheduledAt, groupId ->
                Pair(scheduledAt, groupId)
            }
            .flatMapLatest { (scheduledAt, groupId) ->
                val newScheduledAt = when (scheduledAt) {
                    -1L -> System.currentTimeMillis()
                    else -> scheduledAt
                }
                if (groupId != -1) {
                    interactor.observeExerciseGroup(groupId)
                        .map { group ->
                            CreateScheduledEventUiState.Success(
                                selectedScheduledAt = newScheduledAt,
                                selectedExerciseGroup = group
                            )
                        }
                } else {
                    flowOf(
                        CreateScheduledEventUiState.Success(
                            selectedScheduledAt = newScheduledAt,
                            selectedExerciseGroup = null
                        )
                    )
                }
            }
    }

    fun onEventScheduledAtChanged(value: Long) {
        savedStateHandle[EVENT_SCHEDULED_AT] = value
    }

    fun changeExerciseGroup(group: ExerciseGroup) {
        savedStateHandle[EVENT_EXERCISE_GROUP_ID] = group.id
    }

    fun createExerciseCategory() {
        viewModelScope.launch {
            val eventScheduledAt = savedStateHandle.get<Long>(EVENT_SCHEDULED_AT) ?: 0L
            val eventExerciseGroupId = savedStateHandle.get<Int>(EVENT_EXERCISE_GROUP_ID) ?: 0

            interactor.createScheduledEvent(
                scheduledAt = eventScheduledAt,
                exerciseGroupId = eventExerciseGroupId
            )

            shouldNavigateBack = true
        }
    }
}