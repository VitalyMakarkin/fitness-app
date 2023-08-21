package com.example.fitness.feature.activity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ActivityRoute(
    onSaveCompletedExerciseClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ActivityViewModel = hiltViewModel()
) {
    val uiState by viewModel.activityUiState.collectAsStateWithLifecycle()

    ActivityScreen(
        uiState = uiState,
        onSaveCompletedExerciseClick = onSaveCompletedExerciseClick,
        modifier = modifier
    )
}

@Composable
internal fun ActivityScreen(
    uiState: ActivityUiState,
    onSaveCompletedExerciseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ActivityUiState.Success -> Column {
            Text(text = "Activity: Success!")
            Text(
                text = "[Add]",
                modifier = modifier.clickable { onSaveCompletedExerciseClick() }
            )
        }

        is ActivityUiState.Loading -> Text(
            text = "Activity: Loading!"
        )
    }
}