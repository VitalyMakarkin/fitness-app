package com.example.fitness.feature.createexercisecategory

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

private const val CATEGORY_NAME = "createExerciseCategoryName"
private const val CATEGORY_DESCRIPTION = "createExerciseCategoryDescription"

@HiltViewModel
class CreateExerciseCategoryViewModel @Inject constructor(
    private val interactor: CreateExerciseCategoryInteractor,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val categoryName = savedStateHandle.getStateFlow(CATEGORY_NAME, "")
    val categoryDescription = savedStateHandle.getStateFlow(CATEGORY_DESCRIPTION, "")

    var shouldNavigateBack by mutableStateOf(false)

    fun onCategoryNameChanged(text: String) {
        savedStateHandle[CATEGORY_NAME] = text
    }

    fun onCategoryDescriptionChanged(text: String) {
        savedStateHandle[CATEGORY_DESCRIPTION] = text
    }

    fun createExerciseCategory() {
        viewModelScope.launch {
            val categoryName = savedStateHandle.get<String>(CATEGORY_NAME) ?: ""
            val categoryDescription = savedStateHandle.get<String>(CATEGORY_DESCRIPTION)

            interactor.createExerciseCategory(
                name = categoryName,
                description = categoryDescription
            )

            shouldNavigateBack = true
        }
    }
}