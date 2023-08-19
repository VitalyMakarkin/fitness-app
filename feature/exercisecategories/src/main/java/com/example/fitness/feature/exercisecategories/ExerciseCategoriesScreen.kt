package com.example.fitness.feature.exercisecategories

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ExerciseCategoriesRouter(
    modifier: Modifier = Modifier,
    viewModel: ExerciseCategoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.exerciseCategoriesUiState.collectAsStateWithLifecycle()

    ExerciseCategoriesScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
internal fun ExerciseCategoriesScreen(
    uiState: ExerciseCategoriesUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ExerciseCategoriesUiState.Success -> Text(
            text = "ExerciseCategories: Success!"
        )

        is ExerciseCategoriesUiState.Loading -> Text(
            text = "ExerciseCategories: Loading!"
        )
    }
}