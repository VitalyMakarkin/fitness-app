package com.example.fitness.feature.exercisegroups

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitness.core.design.component.TopNavigationBar

@Composable
internal fun ExerciseGroupsRouter(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExerciseGroupsViewModel = hiltViewModel()
) {
    val uiState by viewModel.exerciseGroupsUiState.collectAsStateWithLifecycle()

    ExerciseGroupsScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@Composable
internal fun ExerciseGroupsScreen(
    uiState: ExerciseGroupsUiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        item {
            TopNavigationBar(
                onBackClick = onBackClick
            )
        }
        item {
            when (uiState) {
                is ExerciseGroupsUiState.Success -> Text(
                    text = "ExerciseGroups: Success!"
                )

                is ExerciseGroupsUiState.Loading -> Text(
                    text = "ExerciseGroups: Loading!"
                )
            }
        }
    }
}