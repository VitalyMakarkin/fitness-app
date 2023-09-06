package com.example.fitness.feature.exercisehistory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = modifier
            .fillMaxSize(),
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            item {
                TopNavigationBar(
                    title = "Completed exercises",
                    onBackClick = onBackClick
                )
            }

            when (uiState) {
                is ExerciseHistoryUiState.Success -> {
                    items(uiState.completedExercises) { exercise ->
                        ExerciseHistoryListItem(
                            name = exercise.name,
                            completedAt = exercise.completedAt
                        )
                    }
                }

                is ExerciseHistoryUiState.Loading -> item {
                    Text(
                        text = "ExerciseHistory: Loading!"
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { onSaveCompletedExerciseClick() },
            modifier = modifier
                .padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add"
            )
        }
    }
}

@Composable
internal fun ExerciseHistoryListItem(
    modifier: Modifier = Modifier,
    name: String = "",
    completedAt: Long = 0
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = name)
        Text(text = completedAt.toString())
    }
}