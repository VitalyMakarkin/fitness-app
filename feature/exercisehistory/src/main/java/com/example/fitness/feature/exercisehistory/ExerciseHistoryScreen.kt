package com.example.fitness.feature.exercisehistory

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ExercisesHistoryRoute(
    modifier: Modifier = Modifier,
    viewModel: ExerciseHistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.exerciseHistoryUiState.collectAsStateWithLifecycle()

    ExerciseHistoryScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
internal fun ExerciseHistoryScreen(
    uiState: ExerciseHistoryUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ExerciseHistoryUiState.Success -> Text(
            text = "ExerciseHistory: Success!"
        )

        is ExerciseHistoryUiState.Loading -> Text(
            text = "ExerciseHistory: Loading!"
        )
    }
}