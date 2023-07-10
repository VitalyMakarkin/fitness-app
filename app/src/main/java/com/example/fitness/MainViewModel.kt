package com.example.fitness

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val mainUiState: StateFlow<MainUiState> = mainUiState().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainUiState.Loading
    )
}

// TODO: update bottom bar if there is a new notification
private fun mainUiState(): Flow<MainUiState> {
    return flow {
        delay(2_000)
        emit(MainUiState.Success)
    }
}

sealed interface MainUiState {
    object Success : MainUiState
    object Loading : MainUiState
}