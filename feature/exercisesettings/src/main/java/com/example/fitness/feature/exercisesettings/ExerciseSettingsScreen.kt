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
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                SettingsGridItem(
                    title = "Exercises completed",
                    subtitle = uiState.activitiesCount.toString(),
                    onClick = { onExerciseHistoryClick() }
                )
            }
            item {
                SettingsGridItem(
                    title = "Exercises categories",
                    subtitle = uiState.exerciseCategoriesCount.toString(),
                    onClick = { onExerciseCategoriesClick() }
                )
            }
            item {
                SettingsGridItem(
                    title = "Exercises groups",
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
internal fun SettingsGridItem(
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
            fontSize = 14.sp, // TODO: move to theme typography
            fontWeight = FontWeight(800), // TODO: move to theme typography
            modifier = modifier
                .padding(start = 8.dp, top = 8.dp)
        )
        if (subtitle.isNotEmpty()) {
            Text(
                text = subtitle,
                fontSize = 20.sp, // TODO: move to theme typography
                fontWeight = FontWeight(800), // TODO: move to theme typography
                modifier = modifier
                    .padding(start = 8.dp)
            )
        }
    }
}