package com.example.fitness.feature.createexercisegroup.dialog.exercisecategoryselection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.feature.createexercisegroup.CreateExerciseGroupInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ExerciseCategorySelectionViewModel @Inject constructor(
    private val interactor: CreateExerciseGroupInteractor
) : ViewModel() {

    val uiState: StateFlow<ExerciseCategorySelectionUiState> =
        exerciseCategorySelectionUiState(
            interactor = interactor
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ExerciseCategorySelectionUiState.Loading
        )

    private fun exerciseCategorySelectionUiState(interactor: CreateExerciseGroupInteractor): Flow<ExerciseCategorySelectionUiState> {
        return interactor.observeExerciseCategories()
            .map { list -> ExerciseCategorySelectionUiState.Success(list) }
    }
}
