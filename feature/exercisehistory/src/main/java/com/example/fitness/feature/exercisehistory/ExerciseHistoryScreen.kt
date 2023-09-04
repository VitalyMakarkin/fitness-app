package com.example.fitness.feature.exercisehistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar

@Composable
internal fun ExercisesHistoryRoute(
    onBackClick: () -> Unit,
    onSaveCompletedExerciseClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExerciseHistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.exerciseHistoryUiState.collectAsStateWithLifecycle()

    ExerciseHistoryScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onSaveCompletedExerciseClick = onSaveCompletedExerciseClick,
        modifier = modifier
    )
}

@Composable
internal fun ExerciseHistoryScreen(
    uiState: ExerciseHistoryUiState,
    onBackClick: () -> Unit,
    onSaveCompletedExerciseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        item {
            TopNavigationBar(
                onBackClick = onBackClick
            )
        }
        item {
            when (uiState) {
                is ExerciseHistoryUiState.Success -> Column {
                    Text(text = "ExerciseHistory: Success!")
                    Text(
                        text = "[Add]",
                        modifier = modifier.clickable { onSaveCompletedExerciseClick() }
                    )
                }

                is ExerciseHistoryUiState.Loading -> Text(
                    text = "ExerciseHistory: Loading!"
                )
            }
        }
    }
}