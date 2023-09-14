package com.example.fitness.feature.exercisegroups

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar
import com.example.fitness.feature.exercisegroups.model.ExerciseGroupUI

@Composable
internal fun ExerciseGroupsRouter(
    onBackClick: () -> Unit,
    onNewExerciseGroupCreateClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExerciseGroupsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ExerciseGroupsScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onNewExerciseGroupCreateClick = onNewExerciseGroupCreateClick,
        modifier = modifier
    )
}

@Composable
internal fun ExerciseGroupsScreen(
    uiState: ExerciseGroupsUiState,
    onBackClick: () -> Unit,
    onNewExerciseGroupCreateClick: () -> Unit,
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
                    title = stringResource(R.string.exercise_groups_top_navigation_bar_title),
                    onBackClick = onBackClick
                )
            }

            when (uiState) {
                is ExerciseGroupsUiState.Success -> {
                    items(uiState.exerciseGroups) { group ->
                        ExerciseGroupTile(
                            modifier = modifier,
                            exerciseGroup = group
                        )
                        Spacer(modifier = modifier.height(12.dp))
                    }
                }

                is ExerciseGroupsUiState.Loading -> {
                    item {
                        Text(
                            text = "ExerciseGroups: Loading!"
                        )
                    }
                }
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onNewExerciseGroupCreateClick() }
        ) {
            Text(
                text = stringResource(R.string.exercise_groups_add_group_button),
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@Composable
internal fun ExerciseGroupTile(
    modifier: Modifier = Modifier,
    exerciseGroup: ExerciseGroupUI
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(800),
            text = exerciseGroup.name
        )

        Text(
            modifier = modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            text = exerciseGroup.exerciseCount.toString()
        )
    }
}