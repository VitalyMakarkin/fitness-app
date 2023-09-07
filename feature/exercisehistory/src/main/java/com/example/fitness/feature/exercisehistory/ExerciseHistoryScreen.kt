package com.example.fitness.feature.exercisehistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onSaveCompletedExerciseClick() }
        ) {
            Text(
                text = "Add",
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
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