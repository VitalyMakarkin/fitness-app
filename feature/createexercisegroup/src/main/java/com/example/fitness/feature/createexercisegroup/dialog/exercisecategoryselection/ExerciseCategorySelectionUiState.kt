package com.example.fitness.feature.createexercisegroup.dialog.exercisecategoryselection

import com.example.fitness.core.model.ExerciseCategory

sealed interface ExerciseCategorySelectionUiState {

    data class Success(
        val categories: List<ExerciseCategory>
    ) : ExerciseCategorySelectionUiState

    object Loading : ExerciseCategorySelectionUiState
}
