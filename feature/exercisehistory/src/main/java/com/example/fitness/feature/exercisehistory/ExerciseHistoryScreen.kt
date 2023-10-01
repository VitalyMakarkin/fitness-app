package com.example.fitness.feature.exercisehistory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar
import com.example.fitness.feature.exercisehistory.model.ExerciseUI
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
internal fun ExercisesHistoryRoute(
    onBackClick: () -> Unit,
    onSaveCompletedExerciseClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExerciseHistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ExerciseHistoryScreen(
        modifier = modifier,
        uiState = uiState,
        onBackClick = onBackClick,
        onSaveCompletedExerciseClick = onSaveCompletedExerciseClick,
        onDeleteItem = { id -> viewModel.deleteExercise(id) }
    )
}

@Composable
internal fun ExerciseHistoryScreen(
    modifier: Modifier = Modifier,
    uiState: ExerciseHistoryUiState,
    onBackClick: () -> Unit,
    onSaveCompletedExerciseClick: () -> Unit,
    onDeleteItem: (Long) -> Unit,
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
                    title = stringResource(R.string.exercise_history_top_navigation_bar_title),
                    onBackClick = onBackClick
                )
            }

            when (uiState) {
                is ExerciseHistoryUiState.Success -> {
                    items(uiState.completedExercises) { exercise ->
                        ExerciseTile(
                            modifier = modifier,
                            exercise = exercise,
                            delete = onDeleteItem
                        )
                        Spacer(modifier = modifier.height(12.dp))
                    }
                }

                is ExerciseHistoryUiState.Loading -> {}
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onSaveCompletedExerciseClick() }
        ) {
            Text(
                text = stringResource(R.string.exercise_history_add_button),
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@Composable
internal fun ExerciseTile(
    modifier: Modifier = Modifier,
    exercise: ExerciseUI,
    delete: (Long) -> Unit,
) {
    val formatter = SimpleDateFormat(
        stringResource(id = R.string.exercise_short_datetime_format),
        Locale.ROOT
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = modifier,
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                text = exercise.name
            )
            IconButton(onClick = { delete(exercise.id) }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Delete"
                )
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = modifier,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                text = formatter.format(exercise.completedAt)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    text = exercise.score.toString(),
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Score"
                )
            }
        }
    }
}