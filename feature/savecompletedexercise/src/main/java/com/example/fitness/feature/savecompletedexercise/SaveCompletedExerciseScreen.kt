package com.example.fitness.feature.savecompletedexercise

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            item {
                TopNavigationBar(
                    title = "New completed exercise",
                    onBackClick = onBackClick
                )
            }
            item {
                when (uiState) {
                    is SaveCompletedExerciseUiState.Success -> Column {
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = uiState.selectedExerciseCategory?.name ?: "Not selected",
                            onValueChange = { },
                            label = { Text(text = "Category") },
                            readOnly = true,
                            interactionSource = remember { MutableInteractionSource() }
                                .also { interactionSource ->
                                    LaunchedEffect(interactionSource) {
                                        interactionSource.interactions.collect {
                                            if (it is PressInteraction.Release) {
                                                showExerciseCategorySelectionDialog = true
                                            }
                                        }
                                    }
                                }
                        )
                    }

                    is SaveCompletedExerciseUiState.Loading -> {}
                }
            }
            item {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = exerciseName,
                    onValueChange = { text -> onExerciseNameChanged(text) },
                    label = { Text(text = "Name") }
                )
            }
            item {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = exerciseCompletedAt,
                    onValueChange = { text -> onExerciseCompletedAtChanged(text) },
                    label = { Text(text = "Completed at") }
                )
            }
            item {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = exerciseSets,
                    onValueChange = { text -> onExerciseSetsChanged(text) },
                    label = { Text(text = "Sets") }
                )
            }
            item {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = exerciseReps,
                    onValueChange = { text -> onExerciseRepsChanged(text) },
                    label = { Text(text = "Reps") }
                )
            }
            item {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = exerciseDuration,
                    onValueChange = { text -> onExerciseDurationChanged(text) },
                    label = { Text(text = "Duration") }
                )
            }
            item {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = exerciseScore,
                    onValueChange = { text -> onExerciseScoreChanged(text) },
                    label = { Text(text = "Score") }
                )
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onSaveClick() }
        ) {
            Text(
                text = "Save",
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}