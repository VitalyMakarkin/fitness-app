package com.example.fitness.feature.savecompletedexercise

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.core.model.ExerciseCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val EXERCISE_CATEGORY_ID = "saveCompletedExerciseExerciseCategoryId"

@HiltViewModel
class SaveCompletedExerciseViewModel @Inject constructor(
    private val interactor: SaveCompletedExerciseInteractor,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val exerciseCategoryId = savedStateHandle.getStateFlow(EXERCISE_CATEGORY_ID, -1)

    val saveCompletedExerciseUiState: StateFlow<SaveCompletedExerciseUiState> =
        saveCompletedExerciseUiState(
            interactor = interactor
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SaveCompletedExerciseUiState.Loading
        )

    private fun saveCompletedExerciseUiState(interactor: SaveCompletedExerciseInteractor): Flow<SaveCompletedExerciseUiState> {
        return savedStateHandle.getStateFlow(EXERCISE_CATEGORY_ID, -1).flatMapConcat { categoryId ->
            if (categoryId != -1) {
                interactor.observeExerciseCategory(exerciseCategoryId.value)
                    .map { category -> SaveCompletedExerciseUiState.Success(category) }
            } else {
                flow { emit(SaveCompletedExerciseUiState.Success(null)) }
            }
        }
    }

    fun changeExerciseCategory(category: ExerciseCategory) {
        savedStateHandle[EXERCISE_CATEGORY_ID] = category.id
    }

    fun saveExercise() {
        viewModelScope.launch {
            interactor.saveExercise()
        }
    }
}