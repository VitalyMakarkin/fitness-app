package com.example.fitness.feature.exercisehistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    LazyColumn {
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
                        name = exercise.exerciseCategoryId.toString(),
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

//        item {
//            when (uiState) {
//                is ExerciseHistoryUiState.Success -> Column {
//                    Text(text = "ExerciseHistory: Success!")
//                    Text(
//                        text = "[Add]",
//                        modifier = modifier.clickable { onSaveCompletedExerciseClick() }
//                    )
//                }
//
//                is ExerciseHistoryUiState.Loading -> Text(
//                    text = "ExerciseHistory: Loading!"
//                )
//            }
//        }
    }
}

@Composable
internal fun ExerciseHistoryListItem(
    modifier: Modifier = Modifier,
    name: String = "",
    completedAt: Long = 0
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = name)
        Text(text = completedAt.toString())
    }
}