package com.example.fitness.feature.savecompletedexercise

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.DatePickerDialog
import com.example.fitness.core.design.component.TopNavigationBar
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.feature.savecompletedexercise.dialog.exercisecategoryselection.ExerciseCategorySelectionDialog
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
internal fun SaveCompletedExerciseRouter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: SaveCompletedExerciseViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val exerciseName by viewModel.exerciseName.collectAsStateWithLifecycle()
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
        onExerciseCompletedAtChanged = { value -> viewModel.onExerciseCompletedAtChanged(value) },
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

@Composable
internal fun SaveCompletedExerciseScreen(
    modifier: Modifier = Modifier,
    uiState: SaveCompletedExerciseUiState,
    onBackClick: () -> Unit,
    onExerciseCategoryChanged: (ExerciseCategory) -> Unit,
    exerciseName: String = "",
    onExerciseNameChanged: (String) -> Unit,
    onExerciseCompletedAtChanged: (Long) -> Unit,
    exerciseSets: String = "",
    onExerciseSetsChanged: (String) -> Unit,
    exerciseReps: String = "",
    onExerciseRepsChanged: (String) -> Unit,
    exerciseDuration: String = "",
    onExerciseDurationChanged: (String) -> Unit,
    exerciseScore: String = "5",
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

    var showDatePicker by rememberSaveable {
        mutableStateOf(false)
    }

    if (showDatePicker) {
        DatePickerDialog(
            modifier = modifier,
            initialDateInMillis = System.currentTimeMillis(),
            onDismiss = { showDatePicker = false },
            onConfirm = { selectedDate -> onExerciseCompletedAtChanged(selectedDate) }
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
                    title = stringResource(R.string.save_completed_exercise_top_navigation_bar_title),
                    onBackClick = onBackClick
                )
            }
            when (uiState) {
                is SaveCompletedExerciseUiState.Success -> {
                    item {
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = uiState.selectedExerciseCategory?.name
                                ?: stringResource(R.string.save_completed_exercise_not_selected_category),
                            onValueChange = { },
                            label = { Text(text = stringResource(R.string.save_completed_exercise_text_input_category_label)) },
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
                    item {
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = exerciseName,
                            onValueChange = { text -> onExerciseNameChanged(text) },
                            label = { Text(text = stringResource(R.string.save_completed_exercise_text_input_name_label)) },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                capitalization = KeyboardCapitalization.Sentences
                            )
                        )
                    }
                    item {
                        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = formatter.format(uiState.selectedCompletedAt),
                            onValueChange = { },
                            label = { Text(text = stringResource(R.string.save_completed_exercise_text_input_completed_at_label)) },
                            readOnly = true,
                            interactionSource = remember { MutableInteractionSource() }
                                .also { interactionSource ->
                                    LaunchedEffect(interactionSource) {
                                        interactionSource.interactions.collect {
                                            if (it is PressInteraction.Release) {
                                                showDatePicker = true
                                            }
                                        }
                                    }
                                }
                        )
                    }
                    item {
                        val numberPattern = remember { Regex("^\\d+\$") }
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = exerciseSets,
                            onValueChange = { text ->
                                if (text.isEmpty()) {
                                    onExerciseSetsChanged("0")
                                } else if (text.matches(numberPattern)) {
                                    onExerciseSetsChanged(text)
                                }
                            },
                            label = { Text(text = stringResource(R.string.save_completed_exercise_text_input_sets_label)) }
                        )
                    }
                    item {
                        val numberPattern = remember { Regex("^\\d+\$") }
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = exerciseReps,
                            onValueChange = { text ->
                                if (text.isEmpty()) {
                                    onExerciseRepsChanged("0")
                                } else if (text.matches(numberPattern)) {
                                    onExerciseRepsChanged(text)
                                }
                            },
                            label = { Text(text = stringResource(R.string.save_completed_exercise_text_input_reps_label)) }
                        )
                    }
                    item {
                        val numberPattern = remember { Regex("^\\d+\$") }
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = exerciseDuration,
                            onValueChange = { text ->
                                if (text.isEmpty()) {
                                    onExerciseDurationChanged("0")
                                } else if (text.matches(numberPattern)) {
                                    onExerciseDurationChanged(text)
                                }
                            },
                            label = { Text(text = stringResource(R.string.save_completed_exercise_text_input_duration_label)) }
                        )
                    }
                    item {
                        val numberPattern = remember { Regex("^[1-5]+\$") }
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = exerciseScore,
                            onValueChange = { text ->
                                if (text.isEmpty()) {
                                    onExerciseScoreChanged("5")
                                } else if (text.matches(numberPattern) && text.length == 1) {
                                    onExerciseScoreChanged(text)
                                }
                            },
                            label = { Text(text = stringResource(R.string.save_completed_exercise_text_input_score_label)) }
                        )
                    }
                }

                is SaveCompletedExerciseUiState.Loading -> {}
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onSaveClick() }
        ) {
            Text(
                text = stringResource(R.string.save_completed_exercise_save_button),
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}