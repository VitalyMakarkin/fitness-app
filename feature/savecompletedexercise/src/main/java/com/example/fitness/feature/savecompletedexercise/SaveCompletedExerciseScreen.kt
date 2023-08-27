package com.example.fitness.feature.savecompletedexercise

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.feature.savecompletedexercise.dialog.exercisecategoryselection.ExerciseCategorySelectionDialog

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
        onExerciseCategoryChanged = { category -> viewModel.changeExerciseCategory(category) },
        onSaveClick = { viewModel.saveExercise() },
        modifier = modifier
    )
}

@Composable
internal fun SaveCompletedExerciseScreen(
    uiState: SaveCompletedExerciseUiState,
    onBackClick: () -> Unit,
    onExerciseCategoryChanged: (ExerciseCategory) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showExerciseCategorySelectionDialog by rememberSaveable {
        mutableStateOf(false)
    }

    if (showExerciseCategorySelectionDialog) {
        ExerciseCategorySelectionDialog(
            onDismiss = { showExerciseCategorySelectionDialog = false },
            onExerciseCategoryClicked = { category -> onExerciseCategoryChanged(category) }
        )
    }

    Column {
        Text(
            text = "[Back]",
            modifier = modifier.clickable { onBackClick() }
        )
        when (uiState) {
            is SaveCompletedExerciseUiState.Success -> Column {
                Text(
                    text = "Category: ${uiState.selectedExerciseCategory?.name ?: "Not selected"}",
                    modifier.clickable { showExerciseCategorySelectionDialog = true }
                )
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