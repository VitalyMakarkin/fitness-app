package com.example.fitness.feature.createexercisegroup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness.core.model.ExerciseGroupItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

private const val GROUP_NAME = "createExerciseGroupName"

@HiltViewModel
class CreateExerciseGroupViewModel @Inject constructor(
    private val interactor: CreateExerciseGroupInteractor,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val categoryName = savedStateHandle.getStateFlow(GROUP_NAME, "")

    var shouldNavigateBack by mutableStateOf(false)

    fun onCategoryNameChanged(text: String) {
        savedStateHandle[GROUP_NAME] = text
    }

    fun addExercise(exerciseGroupItem: ExerciseGroupItem) {
        // TODO
    }

    fun createExerciseCategory() {
        viewModelScope.launch {
            val categoryName = savedStateHandle.get<String>(GROUP_NAME) ?: ""

            interactor.createExerciseGroup(
                name = categoryName
            )

            shouldNavigateBack = true
        }
    }
}