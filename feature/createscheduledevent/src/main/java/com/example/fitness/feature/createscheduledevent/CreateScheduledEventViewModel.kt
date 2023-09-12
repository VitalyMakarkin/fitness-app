package com.example.fitness.feature.createscheduledevent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

private const val EVENT_SCHEDULED_AT = "createScheduledEventScheduledAt"
private const val EVENT_EXERCISE_GROUP_ID = "createScheduledEventExerciseGroupId"

@HiltViewModel
class CreateScheduledEventViewModel @Inject constructor(
    private val interactor: CreateScheduledEventInteractor,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val scheduledAt = savedStateHandle.getStateFlow(EVENT_SCHEDULED_AT, 0L)
    val exerciseGroupId = savedStateHandle.getStateFlow(EVENT_EXERCISE_GROUP_ID, 0)

    var shouldNavigateBack by mutableStateOf(false)

    fun onEventScheduledAtChanged(text: String) {
        savedStateHandle[EVENT_SCHEDULED_AT] = text.toLong()
    }

    fun onEventExerciseGroupChanged(text: String) {
        savedStateHandle[EVENT_EXERCISE_GROUP_ID] = text.toInt()
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