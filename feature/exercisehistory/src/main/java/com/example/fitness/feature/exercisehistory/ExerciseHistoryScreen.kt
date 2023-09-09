package com.example.fitness.feature.exercisehistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import com.example.fitness.feature.exercisehistory.model.ExerciseUI

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
                        ExerciseCategoryTile(
                            modifier = modifier,
                            exercise = exercise
                        )
                        Spacer(modifier = modifier.height(12.dp))
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
internal fun ExerciseCategoryTile(
    modifier: Modifier = Modifier,
    exercise: ExerciseUI
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            fontSize = 20.sp, // TODO: move to theme typography
            fontWeight = FontWeight(800), // TODO: move to theme typography
            text = exercise.name
        )

        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp),
            fontSize = 20.sp, // TODO: move to theme typography
            fontWeight = FontWeight(400), // TODO: move to theme typography
            text = exercise.completedAt.toString()
        )
    }
}