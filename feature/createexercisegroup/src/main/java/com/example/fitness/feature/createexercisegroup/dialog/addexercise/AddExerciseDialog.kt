package com.example.fitness.feature.createexercisegroup.dialog.addexercise

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar
import com.example.fitness.core.model.ExerciseCategory
import com.example.fitness.core.model.ExerciseGroupItem
import com.example.fitness.feature.createexercisegroup.R
import com.example.fitness.feature.createexercisegroup.dialog.exercisecategoryselection.ExerciseCategorySelectionDialog

@Composable
internal fun AddExerciseDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirm: (ExerciseGroupItem) -> Unit,
    viewModel: AddExerciseViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val exerciseName by viewModel.exerciseName.collectAsStateWithLifecycle()
    val exerciseSets by viewModel.exerciseSets.collectAsStateWithLifecycle()
    val exerciseReps by viewModel.exerciseReps.collectAsStateWithLifecycle()
    val exerciseDuration by viewModel.exerciseDuration.collectAsStateWithLifecycle()

    AddExerciseDialog(
        modifier = modifier,
        uiState = uiState,
        onDismiss = onDismiss,
        onExerciseCategoryChanged = { category -> viewModel.changeExerciseCategory(category) },
        exerciseName = exerciseName,
        onExerciseNameChanged = { text -> viewModel.onExerciseNameChanged(text) },
        exerciseSets = exerciseSets.toString(),
        onExerciseSetsChanged = { text -> viewModel.onExerciseSetsChanged(text) },
        exerciseReps = exerciseReps.toString(),
        onExerciseRepsChanged = { text -> viewModel.onExerciseRepsChanged(text) },
        exerciseDuration = exerciseDuration.toString(),
        onExerciseDurationChanged = { text -> viewModel.onExerciseDurationChanged(text) },
        onConfirmClick = {
            onConfirm(viewModel.getConfirmedNewExercise())
            viewModel.shouldNavigateBack = true
        },
        shouldNavigateBack = viewModel.shouldNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
internal fun AddExerciseDialog(
    modifier: Modifier = Modifier,
    uiState: AddExerciseUiState,
    onDismiss: () -> Unit,
    onExerciseCategoryChanged: (ExerciseCategory) -> Unit,
    exerciseName: String = "",
    onExerciseNameChanged: (String) -> Unit,
    exerciseSets: String = "",
    onExerciseSetsChanged: (String) -> Unit,
    exerciseReps: String = "",
    onExerciseRepsChanged: (String) -> Unit,
    exerciseDuration: String = "",
    onExerciseDurationChanged: (String) -> Unit,
    onConfirmClick: () -> Unit,
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
            onDismiss()
        }
    }

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    item {
                        TopNavigationBar(
                            title = stringResource(R.string.add_exercise_dialog_top_navigation_bar_title),
                            onBackClick = { onDismiss() }
                        )
                    }
                    item {
                        when (uiState) {
                            is AddExerciseUiState.Success -> Column {
                                OutlinedTextField(
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    value = uiState.selectedExerciseCategory?.name
                                        ?: stringResource(R.string.add_exercise_dialog_category_not_selected),
                                    onValueChange = { },
                                    label = { Text(text = stringResource(R.string.add_exercise_dialog_text_input_category_label)) },
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

                            is AddExerciseUiState.Loading -> {}
                        }
                    }
                    item {
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = exerciseName,
                            onValueChange = { text -> onExerciseNameChanged(text) },
                            label = { Text(text = stringResource(R.string.add_exercise_dialog_text_input_name_label)) }
                        )
                    }
                    item {
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = exerciseSets,
                            onValueChange = { text -> onExerciseSetsChanged(text) },
                            label = { Text(text = stringResource(R.string.add_exercise_dialog_text_input_sets_label)) }
                        )
                    }
                    item {
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = exerciseReps,
                            onValueChange = { text -> onExerciseRepsChanged(text) },
                            label = { Text(text = stringResource(R.string.add_exercise_dialog_text_input_reps_label)) }
                        )
                    }
                    item {
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            value = exerciseDuration,
                            onValueChange = { text -> onExerciseDurationChanged(text) },
                            label = { Text(text = stringResource(R.string.add_exercise_dialog_text_input_duration_label)) }
                        )
                    }
                }
                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = { onConfirmClick() }
                ) {
                    Text(
                        text = stringResource(R.string.add_exercise_dialog_confirm_button),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600)
                    )
                }
            }
        }
    }
}