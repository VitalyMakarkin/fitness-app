package com.example.fitness.feature.savecompletedexercise

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.feature.savecompletedexercise.dialog.exercisecategoryselection.ExerciseCategorySelectionDialog

@Composable
internal fun SaveCompletedExerciseRouter(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SaveCompletedExerciseViewModel = hiltViewModel()
) {
    val uiState by viewModel.saveCompletedExerciseUiState.collectAsStateWithLifecycle()

    val exerciseName by viewModel.exerciseName.collectAsStateWithLifecycle()
    val exerciseCompletedAt by viewModel.exerciseCompletedAt.collectAsStateWithLifecycle()
    val exerciseSets by viewModel.exerciseSets.collectAsStateWithLifecycle()
    val exerciseReps by viewModel.exerciseReps.collectAsStateWithLifecycle()
    val exerciseDuration by viewModel.exerciseDuration.collectAsStateWithLifecycle()
    val exerciseScore by viewModel.exerciseScore.collectAsStateWithLifecycle()

    SaveCompletedExerciseScreen(
        modifier = modifier,
        uiState = uiState,
        onBackClick = onBackClick,
        onExerciseCategoryChanged = { category -> viewModel.changeExerciseCategory(category) },
        exerciseName = exerciseName,
        onExerciseNameChanged = { text -> viewModel.onExerciseNameChanged(text) },
        exerciseCompletedAt = exerciseCompletedAt.toString(),
        onExerciseCompletedAtChanged = { text -> viewModel.onExerciseCompletedAtChanged(text) },
        exerciseSets = exerciseSets.toString(),
        onExerciseSetsChanged = { text -> viewModel.onExerciseSetsChanged(text) },
        exerciseReps = exerciseReps.toString(),
        onExerciseRepsChanged = { text -> viewModel.onExerciseRepsChanged(text) },
        exerciseDuration = exerciseDuration.toString(),
        onExerciseDurationChanged = { text -> viewModel.onExerciseDurationChanged(text) },
        exerciseScore = exerciseScore.toString(),
        onExerciseScoreChanged = { text -> viewModel.onExerciseScoreChanged(text) },
        onSaveClick = { viewModel.saveExercise() },
        shouldNavigateBack = viewModel.shouldNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SaveCompletedExerciseScreen(
    modifier: Modifier = Modifier,
    uiState: SaveCompletedExerciseUiState,
    onBackClick: () -> Unit,
    onExerciseCategoryChanged: (ExerciseCategory) -> Unit,
    exerciseName: String = "",
    onExerciseNameChanged: (String) -> Unit,
    exerciseCompletedAt: String = "",
    onExerciseCompletedAtChanged: (String) -> Unit,
    exerciseSets: String = "",
    onExerciseSetsChanged: (String) -> Unit,
    exerciseReps: String = "",
    onExerciseRepsChanged: (String) -> Unit,
    exerciseDuration: String = "",
    onExerciseDurationChanged: (String) -> Unit,
    exerciseScore: String = "",
    onExerciseScoreChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    shouldNavigateBack: Boolean = false
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

    LaunchedEffect(shouldNavigateBack) {
        if (shouldNavigateBack) {
            onBackClick()
        }
    }

    LazyColumn {
        item {
            TopNavigationBar(
                title = "Save completed exercise",
                onBackClick = onBackClick
            )
        }
        item {
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
        item {
            TextField(
                value = exerciseName,
                onValueChange = { text -> onExerciseNameChanged(text) },
                label = { Text(text = "Name") }
            )
        }
        item {
            TextField(
                value = exerciseCompletedAt,
                onValueChange = { text -> onExerciseCompletedAtChanged(text) },
                label = { Text(text = "Completed at") }
            )
        }
        item {
            TextField(
                value = exerciseSets,
                onValueChange = { text -> onExerciseSetsChanged(text) },
                label = { Text(text = "Sets") }
            )
        }
        item {
            TextField(
                value = exerciseReps,
                onValueChange = { text -> onExerciseRepsChanged(text) },
                label = { Text(text = "Reps") }
            )
        }
        item {
            TextField(
                value = exerciseDuration,
                onValueChange = { text -> onExerciseDurationChanged(text) },
                label = { Text(text = "Duration") }
            )
        }
        item {
            TextField(
                value = exerciseScore,
                onValueChange = { text -> onExerciseScoreChanged(text) },
                label = { Text(text = "Score") }
            )
        }
    }
}