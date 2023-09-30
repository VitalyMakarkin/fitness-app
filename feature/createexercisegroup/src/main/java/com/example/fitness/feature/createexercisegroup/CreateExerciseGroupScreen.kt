package com.example.fitness.feature.createexercisegroup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar
import com.example.fitness.core.model.ExerciseGroupItem
import com.example.fitness.feature.createexercisegroup.dialog.addexercise.AddExerciseDialog

@Composable
internal fun CreateExerciseGroupRouter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: CreateExerciseGroupViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val categoryName by viewModel.categoryName.collectAsStateWithLifecycle()

    CreateExerciseGroupScreen(
        modifier = modifier,
        uiState = uiState,
        onBackClick = onBackClick,
        onCreate = { viewModel.createExerciseCategory() },
        categoryName = categoryName,
        onCategoryNameChanged = { text -> viewModel.onCategoryNameChanged(text) },
        onConfirmAddExercise = { exercise -> viewModel.addExercise(exercise) },
        shouldNavigateBack = viewModel.shouldNavigateBack
    )
}

@Composable
internal fun CreateExerciseGroupScreen(
    modifier: Modifier = Modifier,
    uiState: CreateExerciseGroupUiState,
    onBackClick: () -> Unit,
    onCreate: () -> Unit,
    categoryName: String = "",
    onCategoryNameChanged: (String) -> Unit,
    onConfirmAddExercise: (ExerciseGroupItem) -> Unit,
    shouldNavigateBack: Boolean = false
) {
    var showAddExerciseDialog by rememberSaveable {
        mutableStateOf(false)
    }

    if (showAddExerciseDialog) {
        AddExerciseDialog(
            onDismiss = { showAddExerciseDialog = false },
            onConfirm = { exerciseGroupItem -> onConfirmAddExercise(exerciseGroupItem) }
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
                    title = stringResource(R.string.create_exercise_group_top_navigation_bar_title),
                    onBackClick = onBackClick
                )
            }
            item {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = categoryName,
                    onValueChange = { text -> onCategoryNameChanged(text) },
                    label = { Text(text = stringResource(R.string.create_exercise_group_text_input_name_label)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )
            }
            when (uiState) {
                is CreateExerciseGroupUiState.Success -> {
                    items(uiState.exercises) { exercise ->
                        Spacer(
                            modifier = modifier
                                .height(12.dp),
                        )
                        ExerciseTile(
                            modifier = modifier,
                            exercise = exercise
                        )
                    }
                }

                is CreateExerciseGroupUiState.Loading -> {}
            }
            item {
                IconButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = { showAddExerciseDialog = true }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(R.string.create_exercise_group_add_exercise_button)
                        )
                        Spacer(
                            modifier = modifier.width(4.dp)
                        )
                        Text(
                            text = stringResource(R.string.create_exercise_group_add_exercise_button),
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600)
                        )
                    }
                }
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onCreate() }
        ) {
            Text(
                text = stringResource(R.string.create_exercise_group_create_group_button),
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@Composable
internal fun ExerciseTile(
    modifier: Modifier = Modifier,
    exercise: ExerciseGroupItem
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(800),
            text = exercise.name
        )
    }
}