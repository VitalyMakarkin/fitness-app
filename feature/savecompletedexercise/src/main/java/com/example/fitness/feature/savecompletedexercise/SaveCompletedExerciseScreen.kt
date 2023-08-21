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
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SaveCompletedExerciseViewModel = hiltViewModel()
) {
    val uiState by viewModel.saveCompletedExerciseUiState.collectAsStateWithLifecycle()

    SaveCompletedExerciseScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onSaveClick = { viewModel.saveExercise() },
        modifier = modifier
    )
}

@Composable
internal fun SaveCompletedExerciseScreen(
    uiState: SaveCompletedExerciseUiState,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "[Back]",
            modifier = modifier.clickable { onBackClick() }
        )
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
}