package com.example.fitness.feature.exercisesettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
        is ExerciseSettingsUiState.Success -> LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ExerciseSettingTile(
                    title = stringResource(R.string.exercise_settings_exercise_completed_tile),
                    subtitle = uiState.activitiesCount.toString(),
                    onClick = { onExerciseHistoryClick() }
                )
            }
            item {
                ExerciseSettingTile(
                    title = stringResource(R.string.exercise_settings_exercise_categories_tile),
                    subtitle = uiState.exerciseCategoriesCount.toString(),
                    onClick = { onExerciseCategoriesClick() }
                )
            }
            item {
                ExerciseSettingTile(
                    title = stringResource(R.string.exercise_settings_exercise_groups_tile),
                    subtitle = uiState.exerciseGroupsCount.toString(),
                    onClick = { onExerciseGroupsClick() }
                )
            }
        }

        is ExerciseSettingsUiState.Loading -> Text(
            text = "ExerciseSettings: Loading!"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExerciseSettingTile(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String = "",
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(200.dp),
        onClick = onClick
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight(800),
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        )
        if (subtitle.isNotEmpty()) {
            Text(
                text = subtitle,
                fontSize = 40.sp,
                fontWeight = FontWeight(500),
                modifier = modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            )
        }
    }
}