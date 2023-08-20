package com.example.fitness.feature.exercisecategories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ExerciseCategoriesRouter(
    onNewExerciseCategoryCreateClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExerciseCategoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.exerciseCategoriesUiState.collectAsStateWithLifecycle()

    ExerciseCategoriesScreen(
        uiState = uiState,
        onNewExerciseCategoryCreateClick = onNewExerciseCategoryCreateClick,
        modifier = modifier
    )
}

@Composable
internal fun ExerciseCategoriesScreen(
    uiState: ExerciseCategoriesUiState,
    onNewExerciseCategoryCreateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ExerciseCategoriesUiState.Success -> Column {
            Text(text = "ExerciseCategories: Success!")
            Text(
                text = "[Create new exercise category]",
                modifier.clickable { onNewExerciseCategoryCreateClick() }
            )
        }

        is ExerciseCategoriesUiState.Loading -> Text(
            text = "ExerciseCategories: Loading!"
        )
    }
}