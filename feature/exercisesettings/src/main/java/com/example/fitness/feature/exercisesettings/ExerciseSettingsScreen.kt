package com.example.fitness.feature.exercisesettings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ExerciseSettingsRouter(
    onExerciseHistoryClick: () -> Unit,
    onExerciseCategoriesClick: () -> Unit,
    onExerciseGroupsClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExerciseSettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.exerciseSettingsUiState.collectAsStateWithLifecycle()

    ExerciseSettingsScreen(
        uiState = uiState,
        onExerciseHistoryClick = onExerciseHistoryClick,
        onExerciseCategoriesClick = onExerciseCategoriesClick,
        onExerciseGroupsClick = onExerciseGroupsClick,
        modifier = modifier
    )
}

@Composable
internal fun ExerciseSettingsScreen(
    uiState: ExerciseSettingsUiState,
    onExerciseHistoryClick: () -> Unit,
    onExerciseCategoriesClick: () -> Unit,
    onExerciseGroupsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ExerciseSettingsUiState.Success -> Column {
            Text(
                text = "${uiState.activitiesCount} exercises completed",
                modifier = modifier.clickable {
                    onExerciseHistoryClick()
                }
            )
            Text(
                text = "${uiState.exerciseCategoriesCount} exercise categories",
                modifier = modifier.clickable {
                    onExerciseCategoriesClick()
                }
            )
            Text(
                text = "${uiState.exerciseGroupsCount} exercise groups",
                modifier = modifier.clickable {
                    onExerciseGroupsClick()
                }
            )
        }

        is ExerciseSettingsUiState.Loading -> Text(
            text = "ExerciseSettings: Loading!"
        )
    }
}