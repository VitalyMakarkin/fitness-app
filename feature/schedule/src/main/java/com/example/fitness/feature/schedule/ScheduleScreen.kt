package com.example.fitness.feature.schedule

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ScheduleRoute(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val uiState by viewModel.scheduleUiState.collectAsStateWithLifecycle()

    ScheduleScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
internal fun ScheduleScreen(
    uiState: ScheduleUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ScheduleUiState.Success -> Text(
            text = "Schedule: Success!"
        )

        is ScheduleUiState.Loading -> Text(
            text = "Schedule: Loading!"
        )
    }
}