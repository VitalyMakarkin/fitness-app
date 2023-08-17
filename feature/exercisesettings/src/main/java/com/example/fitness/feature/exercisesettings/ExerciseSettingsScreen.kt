package com.example.fitness.feature.exercisesettings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ExerciseSettingsRouter(
    modifier: Modifier = Modifier,
    viewModel: ExerciseSettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.exerciseSettingsUiState.collectAsStateWithLifecycle()

    ExerciseSettingsScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
internal fun ExerciseSettingsScreen(
    uiState: ExerciseSettingsUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ExerciseSettingsUiState.Success -> Text(
            text = "ExerciseSettings: Success!"
        )

        is ExerciseSettingsUiState.Loading -> Text(
            text = "ExerciseSettings: Loading!"
        )
    }
}