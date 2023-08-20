package com.example.fitness.feature.createexercisecategory

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun CreateExerciseCategoryRouter(
    modifier: Modifier = Modifier,
    viewModel: CreateExerciseCategoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.createExerciseCategoryUiState.collectAsStateWithLifecycle()

    CreateExerciseCategoryScreen(
        uiState = uiState,
        onCreate = { viewModel.createExerciseCategory() },
        modifier = modifier
    )
}

@Composable
internal fun CreateExerciseCategoryScreen(
    uiState: CreateExerciseCategoryUiState,
    onCreate: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is CreateExerciseCategoryUiState.Success -> Text(
            text = "CreateExerciseCategory: [Create]",
            modifier.clickable { onCreate() }
        )

        is CreateExerciseCategoryUiState.Loading -> Text(
            text = "CreateExerciseCategory: Loading!"
        )
    }
}