package com.example.fitness.feature.createexercisecategory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val CATEGORY_NAME = "createExerciseCategoryName"
private const val CATEGORY_DESCRIPTION = "createExerciseCategoryDescription"
private const val CATEGORY_CONTAINS_SETS = "createExerciseCategoryContainsSets"
private const val CATEGORY_CONTAINS_REPS = "createExerciseCategoryContainsReps"
private const val CATEGORY_CONTAINS_DURATION = "createExerciseCategoryContainsDuration"

@HiltViewModel
class CreateExerciseCategoryViewModel @Inject constructor(
    private val interactor: CreateExerciseCategoryInteractor,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val categoryName = savedStateHandle.getStateFlow(CATEGORY_NAME, "")
    val categoryDescription = savedStateHandle.getStateFlow(CATEGORY_DESCRIPTION, "")
    val categoryContainsSets = savedStateHandle.getStateFlow(CATEGORY_CONTAINS_SETS, false)
    val categoryContainsReps = savedStateHandle.getStateFlow(CATEGORY_CONTAINS_REPS, false)
    val categoryContainsDuration = savedStateHandle.getStateFlow(CATEGORY_CONTAINS_DURATION, false)

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
            val categoryName = savedStateHandle.get<String>(CATEGORY_NAME) ?: ""
            val categoryDescription = savedStateHandle.get<String>(CATEGORY_DESCRIPTION)
            val categoryContainsSets = savedStateHandle.get<Boolean>(CATEGORY_CONTAINS_SETS) ?: false
            val categoryContainsReps = savedStateHandle.get<Boolean>(CATEGORY_CONTAINS_REPS) ?: false
            val categoryContainsDuration = savedStateHandle.get<Boolean>(CATEGORY_CONTAINS_DURATION) ?: false

            interactor.createExerciseCategory(
                name = categoryName,
                description = categoryDescription,
                containsSets = categoryContainsSets,
                containsReps = categoryContainsReps,
                containsDuration = categoryContainsDuration
            )
        }
    }

    fun onCategoryNameChanged(text: String) {
        savedStateHandle[CATEGORY_NAME] = text
    }

    fun onCategoryDescriptionChanged(text: String) {
        savedStateHandle[CATEGORY_DESCRIPTION] = text
    }

    fun onCategoryContainsSetsChanged(checked: Boolean) {
        savedStateHandle[CATEGORY_CONTAINS_SETS] = checked
    }

    fun onCategoryContainsRepsChanged(checked: Boolean) {
        savedStateHandle[CATEGORY_CONTAINS_REPS] = checked
    }

    fun onCategoryContainsDurationChanged(checked: Boolean) {
        savedStateHandle[CATEGORY_CONTAINS_DURATION] = checked
    }
}