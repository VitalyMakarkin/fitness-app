package com.example.fitness.feature.savecompletedexercise.dialog.exercisecategoryselection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.fitness.core.model.ExerciseCategory

@Composable
internal fun ExerciseCategorySelectionDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onExerciseCategoryClicked: (ExerciseCategory) -> Unit,
    viewModel: ExerciseCategorySelectionViewModel = hiltViewModel()
) {
    val uiState by viewModel.exerciseCategorySelectionUiState.collectAsStateWithLifecycle()

    ExerciseCategorySelectionDialog(
        modifier = modifier,
        onDismiss = onDismiss,
        onExerciseCategoryClicked = onExerciseCategoryClicked,
        uiState = uiState
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun ExerciseCategorySelectionDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onExerciseCategoryClicked: (ExerciseCategory) -> Unit,
    uiState: ExerciseCategorySelectionUiState
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            if (uiState is ExerciseCategorySelectionUiState.Success) {
                LazyColumn(modifier = modifier) {
                    items(uiState.categories) { category ->
                        Text(
                            text = category.name,
                            modifier = modifier
                                .clickable {
                                    onExerciseCategoryClicked(category)
                                    onDismiss()
                                }
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}