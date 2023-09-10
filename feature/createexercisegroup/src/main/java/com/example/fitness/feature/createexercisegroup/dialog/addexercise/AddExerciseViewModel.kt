package com.example.fitness.feature.createexercisegroup.dialog.addexercise

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.feature.createexercisegroup.CreateExerciseGroupInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

private const val EXERCISE_CATEGORY_ID = "addExerciseCategoryId"

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    private val interactor: CreateExerciseGroupInteractor,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val addExerciseUiState: StateFlow<AddExerciseUiState> =
        addExerciseUiState(
            interactor = interactor
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AddExerciseUiState.Loading
        )

    var shouldNavigateBack by mutableStateOf(false)

    private fun addExerciseUiState(interactor: CreateExerciseGroupInteractor): Flow<AddExerciseUiState> {
        return savedStateHandle.getStateFlow(EXERCISE_CATEGORY_ID, -1).flatMapLatest { categoryId ->
            if (categoryId != -1) {
                interactor.observeExerciseCategory(categoryId)
                    .map { category -> AddExerciseUiState.Success(category) }
            } else {
                flow { AddExerciseUiState.Success(null) }
            }
        }
    }

    fun changeExerciseCategory(category: ExerciseCategory) {
        savedStateHandle[EXERCISE_CATEGORY_ID] = category.id
    }

    fun addExercise() {
        // TODO

        shouldNavigateBack = true
    }
}