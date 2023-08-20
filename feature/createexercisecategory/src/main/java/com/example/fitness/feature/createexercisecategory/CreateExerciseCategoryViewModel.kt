package com.example.fitness.feature.createexercisecategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class CreateExerciseCategoryViewModel @Inject constructor(
    private val interactor: CreateExerciseCategoryInteractor
) : ViewModel() {

    val createExerciseCategoryUiState: StateFlow<CreateExerciseCategoryUiState> =
        flow {
            emit(CreateExerciseCategoryUiState.Success)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CreateExerciseCategoryUiState.Loading
        )

    fun createExerciseCategory() {
        viewModelScope.launch {
            interactor.createExerciseCategory()
        }
    }
}