package com.example.fitness.feature.exercisecategories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.feature.exercisecategories.model.mapToExerciseCategoryUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ExerciseCategoriesViewModel @Inject constructor(
    private val interactor: ExerciseCategoriesInteractor
) : ViewModel() {

    val exerciseCategoriesUiState: StateFlow<ExerciseCategoriesUiState> = exerciseCategoriesUiState(
        interactor = interactor
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ExerciseCategoriesUiState.Loading
    )

    private fun exerciseCategoriesUiState(interactor: ExerciseCategoriesInteractor): Flow<ExerciseCategoriesUiState> {
        return interactor.observeExerciseCategories()
            .map { list ->
                ExerciseCategoriesUiState.Success(
                    exerciseCategories = list.map { it.mapToExerciseCategoryUI() }
                )
            }
    }


}