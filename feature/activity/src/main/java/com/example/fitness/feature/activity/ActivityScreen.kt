package com.example.fitness.feature.activity

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ActivityRoute(
    modifier: Modifier = Modifier,
    viewModel: ActivityViewModel = hiltViewModel()
) {
    val uiState by viewModel.activityUiState.collectAsStateWithLifecycle()

    ActivityScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
internal fun ActivityScreen(
    uiState: ActivityUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ActivityUiState.Success -> Text(
            text = "Activity: Success!"
        )

        is ActivityUiState.Loading -> Text(
            text = "Activity: Loading!"
        )
    }
}