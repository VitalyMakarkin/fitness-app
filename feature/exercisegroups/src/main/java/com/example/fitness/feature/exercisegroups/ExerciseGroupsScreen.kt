package com.example.fitness.feature.exercisegroups

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ExerciseGroupsRouter(
    modifier: Modifier = Modifier,
    viewModel: ExerciseGroupsViewModel = hiltViewModel()
) {
    val uiState by viewModel.exerciseGroupsUiState.collectAsStateWithLifecycle()

    ExerciseGroupsScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
internal fun ExerciseGroupsScreen(
    uiState: ExerciseGroupsUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ExerciseGroupsUiState.Success -> Text(
            text = "ExerciseGroups: Success!"
        )

        is ExerciseGroupsUiState.Loading -> Text(
            text = "ExerciseGroups: Loading!"
        )
    }
}