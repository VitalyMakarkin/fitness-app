package com.example.fitness.feature.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
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
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            when (uiState) {
                is ScheduleUiState.Success -> {
                    item {
                        Spacer(modifier = modifier.height(16.dp))
                    }
                }

                is ScheduleUiState.Loading ->
                    item {
                        Text(
                            text = "Schedule: Loading!"
                        )
                    }
            }
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { }
        ) {
            Text(
                text = "Add event",
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}