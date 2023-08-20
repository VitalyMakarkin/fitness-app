package com.example.fitness.feature.savecompletedexercise

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun SaveCompletedExerciseRouter(
    modifier: Modifier = Modifier,
    viewModel: SaveCompletedExerciseViewModel = hiltViewModel()
) {
    val uiState by viewModel.saveCompletedExerciseUiState.collectAsStateWithLifecycle()

    SaveCompletedExerciseScreen(
        uiState = uiState,
        onSaveClick = { viewModel.saveExercise() },
        modifier = modifier
    )
}

@Composable
internal fun SaveCompletedExerciseScreen(
    uiState: SaveCompletedExerciseUiState,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is SaveCompletedExerciseUiState.Success -> Column {
            Text(text = "Categories: ${uiState.exerciseCategories.joinToString(",") { it.id.toString() }}")
            Text(
                text = "[Create]",
                modifier.clickable { onSaveClick() }
            )
        }

        is SaveCompletedExerciseUiState.Loading -> Text(
            text = "SaveCompletedExercise: Loading!"
        )
    }
}