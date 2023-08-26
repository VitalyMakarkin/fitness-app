package com.example.fitness.feature.savecompletedexercise.dialog.exercisecategoryselection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ExerciseCategorySelectionDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    viewModel: ExerciseCategorySelectionViewModel = hiltViewModel()
) {
    val uiState by viewModel.exerciseCategorySelectionUiState.collectAsStateWithLifecycle()

    ExerciseCategorySelectionDialog(
        modifier = modifier,
        onDismiss = onDismiss,
        uiState = uiState
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun ExerciseCategorySelectionDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    uiState: ExerciseCategorySelectionUiState
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
        ) {
            if (uiState is ExerciseCategorySelectionUiState.Success) {
                Column {
                    uiState.categories.forEach { category ->
                        Text(text = category.name)
                    }
                }
            }
        }
    }
}